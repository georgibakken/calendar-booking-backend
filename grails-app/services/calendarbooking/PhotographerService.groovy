package calendarbooking

import grails.gorm.transactions.Transactional

import java.time.Instant
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Transactional
class PhotographerService {

    def listPhotographers() {
        Photographer.findAll()
    }

    def findTimeSlot(LocalDate date, Integer durationInMinutes) {
        def availablePhotographers = []
        listPhotographers().each { p ->
            TimeSlot timeSlot

            def sortedAvailabilites = p.availabilities.sort{a, b ->
                a.starts <=> b.starts
            }

            Boolean timeSlotFound = sortedAvailabilites.find {a ->
                if (!isGivenDateAvailable(date, a)) {
                    return false
                }

                Instant bookingStart = a.starts
                Instant bookingEnd = calculateBookingEnds(bookingStart, durationInMinutes)

                if (bookingEnd <= a.ends) {
                    timeSlot = [starts: bookingStart, ends: bookingEnd]
                }

                if(!timeSlot) {
                    return false
                }

                if (p.bookings.isEmpty()) {
                    return true
                } else {
                    p.bookings.each {b ->
                        if (isTimeSlotAlreadyTaken(timeSlot, b)) {
                            timeSlot = [starts: b.ends, ends: calculateBookingEnds(b.ends, durationInMinutes)]
                        }
                    }
                }

                if(!isTimeSlotWithinAvailability(timeSlot, a)) {
                    return false
                } else {
                    return true
                }
            }

            if (timeSlotFound) {
                availablePhotographers.add(
                        [photographer: [id: p.id, name: p.name],
                         timeSlot: [starts: timeSlot.starts.toString(),
                                    ends: timeSlot.ends.toString()]])
            }
        }
        return availablePhotographers
    }

    protected Boolean isGivenDateAvailable(LocalDate date, Availability a) {
        a.starts.toString().contains(date.toString())
    }

    protected Instant calculateBookingEnds(Instant start, Integer duration) {
        start.plus(duration, ChronoUnit.MINUTES)
    }

    protected Boolean isTimeSlotWithinAvailability(TimeSlot timeSlot, Availability a) {
        isWithinRange(timeSlot.starts, a.starts, a.ends) && isWithinRange(timeSlot.ends, a.starts, a.ends)
    }

    protected Boolean isTimeSlotAlreadyTaken(TimeSlot timeSlot, Booking b) {
        isWithinRange(timeSlot.starts, b.starts, b.ends) || isWithinRange(timeSlot.ends, b.starts, b.ends)
    }

    protected Boolean isWithinRange(Instant time, Instant start, Instant end) {
        (time.isAfter(start) || time == start) && (time.isBefore(end) || time == end)
    }
}
