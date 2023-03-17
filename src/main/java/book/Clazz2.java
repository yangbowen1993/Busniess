package book;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Clazz2 {

    @SerializedName("status")
    private String status;
    @SerializedName("error")
    private String error;
    @SerializedName("content")
    private List<ContentDTO> content;

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

    public List<ContentDTO> getContent() {
        return content;
    }

    public void setContent(List<ContentDTO> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "book.Clazz2{" +
                "status='" + status + '\'' +
                ", error='" + error + '\'' +
                ", content=" + content +
                '}';
    }

    public static class ContentDTO {
        @SerializedName("typeId")
        private String typeId;
        @SerializedName("typeName")
        private String typeName;
        @SerializedName("cateId")
        private String cateId;
        @SerializedName("magId")
        private String magId;
        @SerializedName("magName")
        private String magName;
        @SerializedName("pageUrl")
        private String pageUrl;
        @SerializedName("magPic")
        private String magPic;
        @SerializedName("pageThumbUrl")
        private String pageThumbUrl;

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getCateId() {
            return cateId;
        }

        public void setCateId(String cateId) {
            this.cateId = cateId;
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

        public String getPageUrl() {
            return pageUrl;
        }

        public void setPageUrl(String pageUrl) {
            this.pageUrl = pageUrl;
        }

        public String getMagPic() {
            return magPic;
        }

        public void setMagPic(String magPic) {
            this.magPic = magPic;
        }

        public String getPageThumbUrl() {
            return pageThumbUrl;
        }

        public void setPageThumbUrl(String pageThumbUrl) {
            this.pageThumbUrl = pageThumbUrl;
        }

        @Override
        public String toString() {
            return "ContentDTO{" +
                    "typeId='" + typeId + '\'' +
                    ", typeName='" + typeName + '\'' +
                    ", cateId='" + cateId + '\'' +
                    ", magId='" + magId + '\'' +
                    ", magName='" + magName + '\'' +
                    ", pageUrl='" + pageUrl + '\'' +
                    ", magPic='" + magPic + '\'' +
                    ", pageThumbUrl='" + pageThumbUrl + '\'' +
                    '}';
        }
    }
}
