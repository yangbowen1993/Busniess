package book;

import java.util.List;


public class Clazz1 {

    private String status;

    private String error;

    private List<MagazineDTO> magazine;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<MagazineDTO> getMagazine() {
        return magazine;
    }

    public void setMagazine(List<MagazineDTO> magazine) {
        this.magazine = magazine;
    }

    @Override
    public String toString() {
        return "book.Clazz1{" +
                "status='" + status + '\'' +
                ", error='" + error + '\'' +
                ", magazine=" + magazine +
                '}';
    }

    public static class MagazineDTO {

        private String magId;

        private String magName;

        private String magDate;

        private String pubdate;

        @Override
        public String toString() {
            return "MagazineDTO{" +
                    "magId='" + magId + '\'' +
                    ", magName='" + magName + '\'' +
                    ", magDate='" + magDate + '\'' +
                    ", pubdate='" + pubdate + '\'' +
                    '}';
        }

        public String getMagId() {
            return magId;
        }

        public void setMagId(String magId) {
            this.magId = magId;
        }

        public String getMagName() {
            return magName;
        }

        public void setMagName(String magName) {
            this.magName = magName;
        }

        public String getMagDate() {
            return magDate;
        }

        public void setMagDate(String magDate) {
            this.magDate = magDate;
        }

        public String getPubdate() {
            return pubdate;
        }

        public void setPubdate(String pubdate) {
            this.pubdate = pubdate;
        }
    }
}
