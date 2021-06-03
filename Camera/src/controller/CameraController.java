package controller;

import model.Camera;

public class CameraController {
    private Camera cameraModel;

    public CameraController(Camera camera) {
        this.cameraModel = camera;
    }

    public Camera getCameraModel() {
        return this.cameraModel;
    }
}
