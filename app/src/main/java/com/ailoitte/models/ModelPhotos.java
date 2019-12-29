package com.ailoitte.models;

import java.util.List;


public class ModelPhotos {

    private List<Photo> photos = null;

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public class Photo {

        private Integer id;
        private Integer sol;
        private Camera camera;
        private String img_src;
        private String earth_date;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getSol() {
            return sol;
        }

        public void setSol(Integer sol) {
            this.sol = sol;
        }

        public Camera getCamera() {
            return camera;
        }

        public void setCamera(Camera camera) {
            this.camera = camera;
        }

        public String getImg_src() {
            return img_src;
        }

        public void setImg_src(String img_src) {
            this.img_src = img_src;
        }

        public String getEarth_date() {
            return earth_date;
        }

        public void setEarth_date(String earth_date) {
            this.earth_date = earth_date;
        }

        public class Camera {

            private Integer id;
            private String name;
            private Integer rover_id;
            private String full_name;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getRover_id() {
                return rover_id;
            }

            public void setRover_id(Integer rover_id) {
                this.rover_id = rover_id;
            }

            public String getFull_name() {
                return full_name;
            }

            public void setFull_name(String full_name) {
                this.full_name = full_name;
            }
        }

    }
}
