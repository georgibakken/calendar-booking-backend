package calendarbooking

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration

import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

import java.time.Instant
import java.time.temporal.ChronoUnit

@SuppressWarnings('MethodName')
@Rollback
@Integration
class PhotographerServiceIntegrationSpec extends Specification {

    @Autowired PhotographerService service

    def setup() {
    }

    void "find available timeSlot for james"() {
        given:
        Photographer.saveAll(
                new Photographer(name: "Otto Crawford")
                        .addToBookings(new Booking(
                                starts: Instant.parse("2020-11-25T08:30:00Z"),
                                ends: Instant.parse("2020-11-25T09:30:00Z")))
                        .addToAvailabilities(new Availability(
                                starts: Instant.parse("2020-11-25T08:00:00Z"),
                                ends: Instant.parse("2020-11-25T16:00:00Z")))
        )

        when:
        String durationInMinutes = "90"
        TimeSlot availableTimeSlot = service.findTimeSlot(1, durationInMinutes)

        then:
        availableTimeSlot.starts.toString() == "2020-11-25T08:00:00Z"
        availableTimeSlot.ends == Instant.parse("2020-11-25T08:00:00Z").plus(durationInMinutes.toInteger(), ChronoUnit.MINUTES)
    }


}