package td_1

import grails.gorm.transactions.Transactional

@Transactional
class AnnonceService {

    def findAllByOwner(User owner) {
        return Annonce.findAllByAuthor(owner);
    }

    def findOneById(id) {
        return Annonce.findById(id);
    }

    def updateAnnonce(Annonce annonce, Object data) {
        annonce.properties = data;
        annonce.save();
    }

    def deleteAnnonce(Annonce annonce) {
        annonce.delete();
    }

    def createAnnonceFromBody(body) {
        User user = User.findById(body.authorId);
        Annonce annonce = new Annonce(
                title: body.title,
                description: body.description,
                price: body.price,
                images: body.images
        );

        user.addToAnnonces(annonce).save();

        return annonce;
    }
}
