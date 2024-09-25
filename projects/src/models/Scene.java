package models;

    public class Scene {
        private int idScene;
        private String descScene;
        private int idSceneCurrent;
        private String resposta;

        // Getters e Setters
        public int getIdScene() {
            return idScene;
        }

        public void setIdScene(int idScene) {
            this.idScene = idScene;
        }

        public String getDescScene() {
            return descScene;
        }

        public void setDescScene(String descScene) {
            this.descScene = descScene;
        }

        public int getIdSceneCurrent() {
            return idSceneCurrent;
        }

        public void setIdSceneCurrent(int idSceneCurrent) {
            this.idSceneCurrent = idSceneCurrent;
        }

        public String getResposta() {
            return resposta;
        }

        public void setResposta(String resposta) {
            this.resposta = resposta;
        }
    }

