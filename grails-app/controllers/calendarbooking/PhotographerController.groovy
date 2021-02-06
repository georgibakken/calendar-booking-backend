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

    //TODO: CORS(?)
    def findAvailablePhotographers() {
//        def booking = Booking.get(params.bookingId)
        def booking = bookingService.findBooking(params.bookingId)
        def responseData = [errors: "Not found"]

        if (booking) {
            LocalDate date = LocalDate.parse(params.date)
            def availablePhotographers = photographerService.findTimeSlot(date, booking.durationInMinutes)

            if (availablePhotographers) {
                responseData = [availablePhotographers: availablePhotographers]
            }
        }

        render responseData as JSON //TODO: need to render timestamp better here, use view?
    }
}
