£Benefits of using an interface

There are several advantages in utilizing the features of Interfaces in general programming. As you may already know, interfaces define a set of functionality as a rule or a contract. When you implement an interface all of these functionality must be implemented in the concrete class.

The ideas discussed here can be applied using any programming language that supports Interfaces. Java has been used in examples for simplicity.
In general when one writes a simple program, one may not think about the need of using an Interface. But when you are building a larger system or a library which keeps evolving, it is a good idea to use Interface. A particular advantage of using interface in Java is that it allows multiple inheritance.
The full power of Interface is utilized when dependency injection techniques is used to inject required implementation on run time. Using references to interfaces instead of their concrete implementation classes helps in minimizing ripple effects, as the user of an interface reference doesn't have to worry about the changes in the underlying concrete implementation.

££EXAMPLE

To give an example consider our application has a layered architecture with Controller layer on top then Service layer, Repository layer and domain layer in that order. Here controller layer interacts with service layer and service layer interacts with repository layer. Each layer is aware of the services provided by the layer below it. We expose this information using Interface and hide the concrete implementation.
Let us say we have a UserRegistrationService, which registers a new user in our system. Our requirement is to save the user information in a relational database and also in xml files. 

££THE WRONG WAY

If we were not using interface, the UserRegistrationService may be implemented with two functions saveToXML() and saveToDatabase().
```
	public class UserRegistrationService {
    public void saveToXML(UserInfo userInfo) {
        //save to xml using service exposed by Repository layer
    }
    public void saveToDatabase(UserInfo userInfo) {
        //save to db using service exposed by Repository layer
    }
}
```
In this case, the UserRegistrationController should be aware of the concrete implementation of these two functions in UserRegistrationService to use them.

```
public class UserRegistrationController {
    //Controller should be aware of the implementation when no Interface is used
    UserRegistrationService userRegistrationService = new UserRegistrationService();
    public void processRequest(UserInfo userInfo) {
        this.saveToXml(userInfo);
    }
    private void saveToXml(UserInfo userInfo) {
        userRegistrationService.saveToXml(userInfo);
    }
    private void saveToDatabase(UserInfo userInfo) {
        userRegistrationService.saveToDatabase();
    }
}
```
If an additional functionality to save the information as JSON is required then you will have to add a new function saveToJson() in the Service class as well as make changes in the Controller. This adds lots of complication to maintenance of our huge application with hundreds of controllers and services.

££THE RIGHT WAY

When using interface this becomes much simpler. We define our UserRegistrationService like this:
```
	public interface UserRegistrationService {
    public void save();
}
```
The controller layer is only aware of this interface, which has a save method.
Let us say we have two implementations like the following:
```
	public class UserRegistrationServiceXmlImpl implements UserRegistrationService {
    @Override
    public void save(UserInfo userInfo) {
        //save to xml using service exposed by Repository layer
    }
}
	
	public class UserRegistrationServiceRelDbImpl implements UserRegistrationService {
    @Override
    public void save(UserInfo userInfo) {
        //save to relational db using service exposed by Repository layer
    }
}
```
Here we can choose any of these two implementation on run time using dependency injection. One may use @Inject or @Resource annotation to mark an implementation is to be injected. If using Spring, one may also use Xml bean definitions.
```
	//Controller becomes much simpler when using Interfaces in the service layer
public class UserRegistrationController {
    @Resource(name = "userRegistrationServiceXmlImpl")
    UserRegistrationService userRegistrationService;
    public void processRequest(UserInfo userInfo) {
        userRegistrationService.save(userInfo);
    }
	
	    public void setUserRegistrationService(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }
}
```
Now when we need to add the additional functionality of saving to Json, we just add another implementation and select that implementation to be injected. (You may use an IOC{Inversion Of Control} container like Spring to achieve this seamlessly.)
```
	public class UserRegistraionServiceJsonImpl implements UserRegistrationService {
    @Override
    public void save() {
        //save to json using service exposed by Repository layer
    }
}
```
This highly reduces the software modification and extension cost. As changes in one layer does not effect other layer and new functionalities are made available to other layer immediately.
Thus using interface gives you more power over extending and maintaining your application, utilize abstraction and implement good software development practices.

