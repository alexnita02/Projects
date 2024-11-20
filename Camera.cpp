#include "Camera.hpp"

namespace gps {

    //Camera constructor
    Camera::Camera(glm::vec3 cameraPosition, glm::vec3 cameraTarget, glm::vec3 cameraUp) {
        //TODO
        this->cameraPosition = cameraPosition;
        this->cameraTarget = cameraTarget;
        this->cameraUpDirection = cameraUp;
        
    }

    //return the view matrix, using the glm::lookAt() function
    glm::mat4 Camera::getViewMatrix() {
        //TODO

        return glm::lookAt(cameraPosition, cameraTarget, this->cameraUpDirection);
    }

    //update the camera internal parameters following a camera move event
    void Camera::move(MOVE_DIRECTION direction, float speed) {
        //TODO
        glm::vec3 cameraFront = glm::normalize(cameraTarget - cameraPosition);
        glm::vec3 cameraRight = glm::normalize(glm::cross(cameraUpDirection, cameraFront));

        if (direction == MOVE_FORWARD) {
            cameraPosition += cameraFront * speed;
            cameraTarget += cameraFront * speed;
        }
        else if (direction == MOVE_BACKWARD) {
            cameraPosition -= cameraFront * speed;
            cameraTarget -= cameraFront * speed;
        }
        else if (direction == MOVE_LEFT) {
            cameraPosition += cameraRight * speed;
            cameraTarget += cameraRight * speed;
        }
        else if (direction == MOVE_RIGHT) {
            
            cameraPosition -= cameraRight * speed;
            cameraTarget -= cameraRight * speed;
        }
        else if(direction == MOVE_UP) {
            cameraPosition += cameraUpDirection * speed;
            cameraTarget += cameraUpDirection * speed;
        }

        else if(direction == MOVE_DOWN) {
            cameraPosition -= cameraUpDirection * speed;
            cameraTarget -= cameraUpDirection * speed;
        }
    }

    //update the camera internal parameters following a camera rotate event
    //yaw - camera rotation around the y axis
    //pitch - camera rotation around the x axis
    void Camera::rotate(float pitch, float yaw) {
        //TODO
       yaw += yaw;
       pitch += pitch;

        if (pitch > 89.0f)
        {
            pitch = 89.0f;
        }

        if (pitch < -89.0f)
        {
            pitch = -89.0f;
        }

        this->cameraTarget = glm::vec3(cameraPosition.x + sin(glm::radians(yaw)), cameraPosition.y - sin(glm::radians(pitch)), cameraPosition.z - cos(glm::radians(yaw)));

        this->cameraFrontDirection = glm::normalize(this->cameraTarget - this->cameraPosition);

        this->cameraRightDirection = glm::normalize(glm::cross(this->cameraFrontDirection, this->cameraUpDirection));


    }
    }

