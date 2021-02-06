package calendarbooking

import grails.gorm.transactions.Transactional

@Transactional
class BookingService {

    def findBooking( id) {
        Booking.get(id)
    }
}
