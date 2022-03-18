package td_1

import td_1.Illustration;
import grails.gorm.transactions.Transactional
import org.springframework.web.multipart.MultipartFile;

@Transactional
class FileService {

    static final String saveFilePath = "C:\\Users\\doria\\Documents\\Grails\\Lecoincoin\\Back-end\\grails-app\\assets\\";

    def saveFile(Illustration illustration, MultipartFile file, int annonceId) {
        File filePath = new File(saveFilePath + illustration.filename);
        file.transferTo(filePath);
        illustration.annonce = Annonce.get(annonceId);
        return illustration;
    }

    def checkFilename(String filename) {
        final String storedFileName = UUID.randomUUID().toString() + filename.substring(filename.indexOf('.'));

        final char search = '.';
        int count = 0;
        for(int i = 0; i < storedFileName.length(); i++) {
            if(storedFileName.charAt(i) == search) {
                count++;
                if (count > 1) break;
            }
        }

        if (count > 1) throw new Exception("File is kinda sus");
        return storedFileName;
    }

    def deleteFile(String filename) { new File(saveFilePath + filename).delete(); }

    def deleteFileFromDomain(Illustration illustration) {
        this.deleteFile(illustration.filename);
    }
}
