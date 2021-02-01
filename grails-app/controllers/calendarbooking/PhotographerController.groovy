package calendarbooking

class PhotographerController {

    static responseFormats = ['json']
    PhotographerService photographerService

    def index() {
        List<Photographer> photographerList = photographerService.listPhotographers()
        respond photographerList
    }
}
