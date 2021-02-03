package calendarbooking

import grails.gorm.transactions.Transactional
import groovy.time.TimeCategory

import java.time.Instant
import java.time.temporal.ChronoUnit

@Transactional
class PhotographerService {

    def listPhotographers() {
        Photographer.findAll()
    }

    def findTimeSlot(Integer bookingId, String durationInMinutes) {
        // find available photographer
        // next available 09.30

        // duration: 90 minutes -->
        // availability: 08 - 16.00
        // booked: 08.30 - 09.30

        // new availability: starts: 09.30, ends: 16.00
        // 90 minutes < booked.ends && 90 minutes < availability.ends
        // find photographer where


        // List of availabilities (starts, ends)
        // List of bookings (starts, ends)
        // New booking, with durationInMinutes.

        // Find timeslot in availabilities, that doesn't override existing bookings.

        // 1. Check if photographer is free for the requested duration
        // --> get minutes from availabilities & get minutes from bookings
        // --> (sum of availabilityMinutes - sum of bookingMinutes) > newBookingMinutes

        // Ex 1: (8 - 1) > 90
        // Ex 2 (multiple availabilities): (1 + 3 - 1) > 90 == true, but starts and ends are crucial
        // cant do it in the first availability, because 90 > 1
        // Can I convert minutes to timeSlots?


        // Availability start time: 08.00.
        // 90 minutes --> 08.00 & 09.30
        // 1. Check if bookings
        // No bookings: Find next timeslot: 08.00 == starts, 09.30 <= ends.
        // booking

        def availablePhotographers = []
        //check for listPhotographers
        listPhotographers().each { p ->
            TimeSlot timeSlot

            p.availabilities.find {a->
                Instant bookingStart = a.starts
                Instant bookingEnd = calculateBookingEnds(bookingStart, durationInMinutes.toInteger())

                if (bookingEnd <= a.ends) {
                    timeSlot = [
                            starts: bookingStart,
                            ends: bookingEnd
                    ]
                    return true
                }
                return false
            }
            log.info("Found timeslot starts: ${timeSlot.starts}")
            log.info("Found timeslot ends: ${timeSlot.ends}")

            availablePhotographers.add([photographer: [id: p.id, name: p.name], timeSlot: timeSlot])
        }

        return availablePhotographers

        // Comparing/joining/venn diagraming the availabilites and the bookings
        // Can i SQL query myself out of it?


    }

    protected Instant calculateBookingEnds(Instant start, Integer duration) {
        start.plus(duration, ChronoUnit.MINUTES)
    }
}
