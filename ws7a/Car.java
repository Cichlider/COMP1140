public class Car{
    private String manufacturer;
    private String model;
    private double speed;


    public Car(String manufacturer, String model){
        this.manufacturer = manufacturer;
        this.model = model;
        this.speed = 0.0;
    }

    public void accelerate(double amount){
        if(amount > 0){
            this.speed += amount;
        }
    }

    public void decelerate(double amount){
        if(amount > 0){
            this.speed -= amount;
            if(this.speed<0){
                this.speed = 0.0;
            }
        }
    }

    public double getCurrentSpeed(){
        return this.speed;
    }

    public static void main(String[] args){
        Car myCar = new Car("Toyota", "Camry");
        
        System.out.println("Initial speed: " + myCar.getCurrentSpeed());
        
        // Accelerate a few times
        myCar.accelerate(20.0);
        System.out.println("After accelerating by 20: " + myCar.getCurrentSpeed());
        
        myCar.accelerate(15.0);
        System.out.println("After accelerating by 15: " + myCar.getCurrentSpeed());
        
        myCar.accelerate(10.0);
        System.out.println("After accelerating by 10: " + myCar.getCurrentSpeed());
        
        // Decelerate once
        myCar.decelerate(25.0);
        System.out.println("After decelerating by 25: " + myCar.getCurrentSpeed());
        
        // Test deceleration beyond zero
        myCar.decelerate(50.0);
        System.out.println("After decelerating by 50: " + myCar.getCurrentSpeed());
    }
}

