package com.dailycodework.dreamshops.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
//DTO stands for Data Transfer Object. It is a design pattern used to transfer data between layers in an application. A DTO typically contains only the data that needs to be transferred, without any business logic.
//The purpose of a DTO is to encapsulate data and send it across different layers, such as from a service to a controller, or from a service to a client. This helps to separate the internal data structure of the application from what is exposed to the external world (e.g., through an API).
//The decision to use a DTO (Data Transfer Object) for images but not for products or categories can be influenced by several factors related to the complexity of the data, the specific requirements of the application, and how the data is being used. Let's explore why you might choose to use a DTO with images but not with products or categories:
//A DTO can be seen as a form of delegation in that it delegates the responsibility of data transfer between layers of an application to a simplified object. Instead of transferring complex entities (which might have many dependencies, business logic, or even sensitive data), the application uses a DTO to carry only the necessary data across boundaries (e.g., between the service layer and the presentation layer).
public class ImageDto {
    private Long imageId;
    private String imageName;
    private String downloadUrl;
}
