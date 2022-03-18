package td_1

import grails.gorm.transactions.Transactional

@Transactional
class IllustrationService {

    def findOneById(id) {
        return Illustration.findById(id);
    }

    def deleteOne(Illustration illustration) {
        illustration.delete();
    }
}
