package calendarbooking

import grails.converters.JSON

class PhotographerController {

    PhotographerService photographerService

    def index() {
        List<Photographer> photographerList = photographerService.listPhotographers()
        render photographerList as JSON
    }

    def findAvailablePhotographers() {
        def photos = photographerService.findTimeSlot(1, "90")
        def responseData = [errors: "Not found"]

        if (photos) {
            responseData = [availablePhotographers: photos]
        }
        render responseData as JSON
    }
}
