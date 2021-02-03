package calendarbooking

import grails.converters.JSON

class PhotographerController {

    PhotographerService photographerService

    def index() {
        List<Photographer> photographerList = photographerService.listPhotographers()
        render photographerList as JSON
    }

    def findAvailablePhotographers() {
        TimeSlot timeSlots = photographerService.findTimeSlot(1, "90")
        def responseData = [errors: "Not found"]

        if (timeSlots) {
            responseData = [timeSlot: timeSlots]
        }
        render responseData as JSON
    }
}
