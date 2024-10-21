package com.rocs.osd.repository.section;

import com.rocs.osd.domain.section.Section;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * An interface to the Section repository.
 */
public interface SectionRepository extends JpaRepository<Section, Long> {
}
