package td_1

import org.springframework.security.access.annotation.Secured

@Secured("ROLE_ADMIN")
class AnnonceController {

    AnnonceService annonceService;
    UserService userService;
    static allowedMethods = [findByOwner: "GET", findOneById: "GET", updateById: "PUT", deleteById: 'DELETE', create: 'POST'];

    def findAll() {
        render(template: '/annonce/annonces', model: [annonces:Annonce.getAll()]);
    }

    def create() {
        Annonce annonce = annonceService.createAnnonceFromBody(request.JSON);
        annonce.save();

        render(template: '/annonce/annonce', model: [annonce:annonce]);
    }

    def deleteById() {
        try {
            int id = Integer.parseInt(params.id, 10);
            Annonce annonce = annonceService.findOneById(id);

            if (!annonce) throw new Exception("Annonce not found");
            annonceService.deleteAnnonce(annonce);

            render(message: 'Annonce deleted successfully');
        } catch(NumberFormatException e) {
            render(status: 400, message: 'The provided id is not of type number');
        } catch(Exception e) {
            render(status: 400, message: e.message);
        }
    }

    def findOneById() {
        try {
            int id = Integer.parseInt(params.id, 10);
            Annonce annonce = annonceService.findOneById(id);

            if (!annonce) throw new Exception("Annonce not found");

            render(template: '/annonce/annonce', model: [annonce:annonce]);
        } catch(NumberFormatException e) {
            render(status: 400, message: 'The provided id is not of type number');
        } catch(Exception e) {
            render(status: 400, message: e.message);
        }
    }

    def findByOwner() {
        try {
            int id = Integer.parseInt(params.id, 10);

            User user = userService.findOneById(id);
            if (!user) throw new Exception("User not found");

            def annonces = annonceService.findAllByOwner(user);

            render(template: '/annonce/annonces', model: [annonces:annonces]);
        } catch(NumberFormatException e) {
            render(status: 400, message: 'The provided id is not of type number');
        } catch(Exception e) {
            render(status: 400, message: e.message);
        }
    }

    def updateById() {
        try {
            int id = Integer.parseInt(params.id, 10);
            Annonce annonce = annonceService.findOneById(id);

            if (!annonce) throw new Exception("annonce not found");

            annonceService.updateAnnonce(annonce, request.JSON);

            render(template: '/annonce/annonce', model: [annonce:annonce]);
        } catch(NumberFormatException e) {
            render(status: 400, message: 'The provided id is not of type number');
        } catch(Exception e) {
            render(status: 400, message: e.message);
        }
    }
}
