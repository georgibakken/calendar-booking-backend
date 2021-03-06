package calendarbooking

import grails.converters.JSON

import java.time.LocalDate

class PhotographerController {

    PhotographerService photographerService
    BookingService  bookingService

    def index() {
        List<Photographer> photographerList = photographerService.listPhotographers()
        render photographerList as JSON
    }

    def findAvailablePhotographers() {
        def booking = bookingService.findBooking(params.bookingId)
        def responseData = [errors: "Not found"]

        if (booking && booking.durationInMinutes) {
            LocalDate date = LocalDate.parse(params.date)
            def availablePhotographers = photographerService.findTimeSlot(date, booking.durationInMinutes)

            if (availablePhotographers) {
                responseData = [availablePhotographers: availablePhotographers]
            }
        }

        render responseData as JSON
    }
}
