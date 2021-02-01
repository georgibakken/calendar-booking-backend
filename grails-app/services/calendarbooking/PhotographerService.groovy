package calendarbooking

import grails.gorm.transactions.Transactional

@Transactional
class PhotographerService {

    def listPhotographers() {
        Photographer.findAll()
    }
}
