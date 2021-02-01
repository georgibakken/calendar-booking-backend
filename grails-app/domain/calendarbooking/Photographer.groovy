package calendarbooking

class Photographer {

    String name
    static hasMany = [bookings: Booking, availabilities: Availability]

    static constraints = {
    }
}
