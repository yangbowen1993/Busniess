package book;

import java.util.List;


public class LatelyBook {


    private String status;

    private String error;

    private List<MagazineDTO> magazine;

    private List<BrandDTO> brand;

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

    public List<BrandDTO> getBrand() {
        return brand;
    }

    public void setBrand(List<BrandDTO> brand) {
        this.brand = brand;
    }

    public static class MagazineDTO {
        private String magId;
        private String magName;
        private String magDate;
        private String magCover;

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

        public String getMagCover() {
            return magCover;
        }

        public void setMagCover(String magCover) {
            this.magCover = magCover;
        }
    }

    public static class BrandDTO {
        private String magId;
        private String magName;

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
    }
}
