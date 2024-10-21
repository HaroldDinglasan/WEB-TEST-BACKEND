package com.rocs.osd.repository.station;

import com.rocs.osd.domain.station.Station;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * An interface to the Station repository.
 */
public interface StationRepository extends JpaRepository<Station, Long> {

}
