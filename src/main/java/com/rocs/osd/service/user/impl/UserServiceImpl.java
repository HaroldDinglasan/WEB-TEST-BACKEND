package com.rocs.osd.service.user.impl;


import com.rocs.osd.domain.employee.Employee;
import com.rocs.osd.domain.external.External;
import com.rocs.osd.domain.guest.Guest;
import com.rocs.osd.domain.register.Register;
import com.rocs.osd.domain.student.Student;
import com.rocs.osd.domain.user.User;
import com.rocs.osd.domain.user.principal.UserPrincipal;
import com.rocs.osd.exception.domain.OtpExistsException;
import com.rocs.osd.exception.domain.PersonExistsException;
import com.rocs.osd.exception.domain.UserNotFoundException;
import com.rocs.osd.exception.domain.UsernameExistsException;
import com.rocs.osd.repository.employee.EmployeeRepository;
import com.rocs.osd.repository.external.ExternalRepository;
import com.rocs.osd.repository.guest.GuestRepository;
import com.rocs.osd.repository.student.StudentRepository;
import com.rocs.osd.repository.user.UserRepository;
import com.rocs.osd.service.email.EmailService;
import com.rocs.osd.service.login.attempt.LoginAttemptService;
import com.rocs.osd.service.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.rocs.osd.utils.security.enumeration.Role.*;

/**
 *This  class handles user-related tasks such as registration, authentication, password recovery, and  verification One-Time Password
 */
@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private UserRepository userRepository;
    private StudentRepository studentRepository;
    private EmployeeRepository employeeRepository;
    private ExternalRepository externalRepository;
    private GuestRepository guestRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private LoginAttemptService loginAttemptService;
    private EmailService emailService;

    /**
     * Constructor for injecting UserService and EmailService.
     *
     * @param userRepository  the service used for user management
     * @param studentRepository the service used for managing Student
     * @param employeeRepository  the service used for managing Employee
     * @param externalRepository  the service used for managing External
     * @param guestRepository the guest used for managing External
     * @param passwordEncoder the service used for encrypting passwords
     * @param loginAttemptService the service used for managing login
     * @param emailService the service used for sending emails
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           StudentRepository studentRepository,
                           EmployeeRepository employeeRepository,
                           ExternalRepository externalRepository,
                           GuestRepository guestRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           LoginAttemptService loginAttemptService,
                           EmailService emailService) {
        this.employeeRepository = employeeRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.externalRepository = externalRepository;
        this.guestRepository = guestRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginAttemptService = loginAttemptService;
        this.emailService = emailService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByUsername(username);
        if (user == null) {
            LOGGER.error("Username not found...");
            throw new UsernameNotFoundException("Username not found.");
        }
        validateLoginAttempt(user);
        user.setLastLoginDate(new Date());
        this.userRepository.save(user);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        LOGGER.info("User information found...");
        return userPrincipal;
    }
    private void validateLoginAttempt(User user) {
        if(!user.isLocked()) {
            if (loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
                user.setLocked(true);
            } else {
                user.setLocked(false);
            }
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }
    @Override
    public Register register(Register register) throws UsernameNotFoundException, UsernameExistsException, MessagingException, PersonExistsException, UserNotFoundException {
        String username = register.getUser().getUsername();
        String password = register.getUser().getPassword();
        validateNewUsername(username);
        validatePassword(password);
        String otp = generateOTP();
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setJoinDate(new Date());
        user.setActive(true);

        if (register.getEmployee() != null && register.getEmployee().getEmployeeNumber() != null) {
            String userType = register.getEmployee().getEmployeeNumber();
            Employee employeeExists = employeeRepository.findByEmployeeNumber(userType);
            if (employeeExists != null) {
                String email = employeeExists.getEmail();
                emailService.sendNewPasswordEmail(email, otp);
                employeeExists.setUser(user);
                user.setOtp(otp);
                user.setLocked(true);
                user.setRole(ROLE_EMPLOYEE.name());
                user.setAuthorities(Arrays.stream(ROLE_EMPLOYEE.getAuthorities()).toList());
                userRepository.save(user);
            }
        } else if (register.getStudent() != null && register.getStudent().getStudentNumber() != null) {
            String userType = register.getStudent().getStudentNumber();
            Student studentExists = studentRepository.findByStudentNumber(userType);
            if (studentExists != null) {
                String email = studentExists.getEmail();
                emailService.sendNewPasswordEmail(email, otp);
                studentExists.setUser(user);
                user.setOtp(otp);
                user.setLocked(true);
                user.setRole(ROLE_STUDENT.name());
                user.setAuthorities(Arrays.stream(ROLE_STUDENT.getAuthorities()).toList());
                userRepository.save(user);
            }
        } else if (register.getExternal() != null && register.getExternal().getExternalNumber() != null) {
            String userType = register.getExternal().getExternalNumber();
            External externalExists = externalRepository.findByExternalNumber(userType);
            if (externalExists != null) {
                String email = externalExists.getEmail();
                emailService.sendNewPasswordEmail(email, otp);
                externalExists.setUser(user);
                user.setOtp(otp);
                user.setLocked(true);
                user.setRole(ROLE_EMPLOYEE.name());
                user.setAuthorities(Arrays.stream(ROLE_EMPLOYEE.getAuthorities()).toList());
                userRepository.save(user);
            }
        } else if (register.getGuest() != null && register.getGuest().getGuestNumber() != null) {
            String userType = register.getGuest().getGuestNumber();
            String email = register.getGuest().getEmail();
            Guest guest = register.getGuest();

            guest.setUser(user);
            emailService.sendNewPasswordEmail(email, otp);

            user.setOtp(otp);
            user.setLocked(true);
            user.setRole(ROLE_GUEST.name());
            user.setAuthorities(Arrays.stream(ROLE_GUEST.getAuthorities()).toList());

            userRepository.save(user);
            guestRepository.save(guest);
        }
        return register;
    }

    @Override
    public User forgotPassword(User newUser) throws UsernameNotFoundException, MessagingException {
        String username = newUser.getUsername();
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username Not Found!");
        }

        String otp = generateOTP();
        user.setOtp(otp);

        Student studentAccount = studentRepository.findByUser_Id(user.getId());
        Employee employeeAccount = employeeRepository.findByUser_Id(user.getId());
        External externalAccount = externalRepository.findByUser_Id(user.getId());
        Guest guestAccount = guestRepository.findByUser_Id(user.getId());

        if(studentAccount != null && studentAccount.getEmail() != null) {
            emailService.sendNewPasswordEmail(studentAccount.getEmail(), otp);
        } else if(employeeAccount != null && employeeAccount.getEmail() != null) {
            emailService.sendNewPasswordEmail(employeeAccount.getEmail(), otp);
        } else if(externalAccount != null && externalAccount.getEmail() != null) {
            emailService.sendNewPasswordEmail(externalAccount.getEmail(), otp);
        } else if(guestAccount != null && guestAccount.getEmail() != null) {
            emailService.sendNewPasswordEmail(guestAccount.getEmail(), otp);
        } else {
           throw new MessagingException("No email associated with this user for password reset.");
        }
        userRepository.save(user);
         return user;
    }

    @Override
    public User verifyOtpForgotPassword(User newUser) throws UsernameNotFoundException, PersonExistsException, OtpExistsException {
        validatePassword(newUser.getPassword());
        String username = newUser.getUsername();
        String newPassword = passwordEncoder.encode(newUser.getPassword());
        String otp = newUser.getOtp();
        User user = userRepository.findUserByUsername(username);
        if(user.getOtp().equals(otp)){
            user.setPassword(newPassword);
            user.setOtp(null);
        } else {
            throw new OtpExistsException("Incorrect OTP code!");
        }
        return newUser;
    }
    @Override
    public boolean verifyOtp(String username, String otp) {
        User user = userRepository.findUserByUsername(username);
        if (user != null && user.getOtp().equals(otp)) {
            user.setLocked(false);
            user.setOtp(null);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    private void validateNewUsername(String newUsername)
            throws UserNotFoundException, UsernameExistsException, PersonExistsException {
        User userByNewUsername = findUserByUsername(newUsername);
        if (StringUtils.isNotBlank(StringUtils.EMPTY)) {
            User currentUser = findUserByUsername(StringUtils.EMPTY);
            if (currentUser == null) {
                throw new UserNotFoundException("User not found.");
            }
            if (userByNewUsername != null && !userByNewUsername.getId().equals(currentUser.getId())) {
                throw new PersonExistsException("Username already exists.");
            }
        } else {
            if (userByNewUsername != null) {
                throw new PersonExistsException("Username already exists.");
            }
        }
    }

    @Override
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }
    private void validatePassword(String password) throws PersonExistsException {
        String passwordPattern = ".*[^a-zA-Z0-9].*";
        if (!password.matches(passwordPattern)) {
            throw new PersonExistsException("Please create a stronger password. Password should contain special characters.");
        }
    }
    private String generateOTP() {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }

    @Override
    public User findUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username);
    }

    @Override
    public User forgotUsername(String email) throws UsernameNotFoundException, MessagingException {
        User user = findUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Email not associated with any username!");
        }
        String otp = generateOTP();
        user.setOtp(otp);
        userRepository.save(user);
        emailService.sendNewPasswordEmail(email, otp);

        return user;
    }

    private User findUserByEmail(String email) {
        Student studentAccount = studentRepository.findByEmail(email);
        if (studentAccount != null) return studentAccount.getUser();
        Employee employeeAccount = employeeRepository.findByEmail(email);
        if (employeeAccount != null) return employeeAccount.getUser();
        External externalAccount = externalRepository.findByEmail(email);
        if (externalAccount != null) return externalAccount.getUser();
        Guest guestAccount = guestRepository.findByEmail(email);
        if (guestAccount != null) return guestAccount.getUser();

        return null;
    }

    @Override
    public User verifyOtpForgotUsername(User newUser) throws OtpExistsException, UsernameExistsException {
        String otp = newUser.getOtp();
        String newUsername = newUser.getUsername();

        List<User> users = userRepository.findAll();
        User user = users.stream()
                .filter(u -> otp.equals(u.getOtp()))
                .findFirst()
                .orElseThrow(() -> new OtpExistsException("Invalid OTP code!"));

        if (userRepository.findUserByUsername(newUsername) != null) {
            throw new UsernameExistsException("Username already exists!");
        }
        System.out.println(newUsername);
        user.setUsername(newUsername);
        user.setOtp(null);
        userRepository.save(user);

        return user;
    }


}

