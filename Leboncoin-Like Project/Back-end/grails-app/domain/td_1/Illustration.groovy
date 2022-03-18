package td_1

import org.springframework.beans.factory.annotation.Autowired

class Illustration {

    String filename;

    FileService fileService;

    static transients = ['fileService'];
    static belongsTo = [annonce: Annonce];

    static mapping = {
        autowire true
    }

    static constraints = {
        filename nullable: false, blank: false;
    }

    def afterDelete() {
        fileService.deleteFileFromDomain(this);
    }
}
