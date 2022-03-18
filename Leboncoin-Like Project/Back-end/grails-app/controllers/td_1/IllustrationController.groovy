package td_1

import org.springframework.security.access.annotation.Secured

@Secured("ROLE_ADMIN")
class IllustrationController {

    IllustrationService illustrationService;
    FileService fileService;
    AnnonceService annonceService;
    static allowedMethods = [findOneById: "GET", create: "POST", deleteOneById: "DELETE"];

    def findOneById() {
        try {
            int id = Integer.parseInt(params.id, 10);
            Illustration illustration = illustrationService.findOneById(id);

            if (!illustration) throw new Exception("Illustration not found");

            render(template: '/illustration/illustration', model: [illustration: illustration]);
        } catch(NumberFormatException e) {
            render(status: 400, message: 'The provided id is not of type number');
        } catch(Exception e) {
            render(status: 400, message: e.message);
        }
    }

    def deleteOneById() {
        try {
            int id = Integer.parseInt(params.id, 10);
            Illustration illustration = illustrationService.findOneById(id);

            if (!illustration) throw new Exception("Illustration not found");
            illustrationService.deleteOne(illustration);

            render(message: 'Illustration deleted successfully');
        } catch(NumberFormatException e) {
            render(status: 400, message: 'The provided id is not of type number');
        } catch(Exception e) {
            render(status: 400, message: e.message);
        }
    }

    def findAll() {
        render(template: '/illustration/illustrations', model: [illustrations: Illustration.getAll()]);
    }

    def create() {
        try {
            int annonceId = Integer.parseInt(params.annonceId, 10);
            Annonce annonce = annonceService.findOneById(annonceId);
            if (!annonce) throw new Exception("Annonce not found");

            def file = request.getFile('file');
            def filename = fileService.checkFilename(file.originalFilename);
            fileService.saveFile(new Illustration(filename: filename), file, annonceId).save();

            render(message: 'File created');
        } catch(NumberFormatException e) {
            render(status: 400, message: 'The provided id is not of type number');
        } catch(Exception e) {
            render(status: 400, message: e.message);
        }
    }

}
