public class NotMain {
    public static void main(String[] args) {
        DoorTile door = new DoorTile();
        Entity lever1 = new Entity(), lever2 = new Entity(), lever3 = new Entity();

        // ganz viel Code

        if (!door.isOpen() && (lever1.isOn() && (lever2.isOn() || lever3.isOn()))) door.open();
        h doorhandle = helper(door, lever1, lever2, lever3);
        doorhandle.test().accept();

        // ganz viel Code
    }
    static h helper(DoorTile d, Entity l1, Entity l2, Entity l3) {
        return () -> {
            if (!d.isOpen() && l1.isOn() && l2.isOn() && l3.isOn()) {
                return () -> {
                    d.open();
                };
            }
            return () -> {
                d.open();
            };
        };
    }
    
}

class DoorTile {
    public boolean isOpen() { return false; }
    public void open() { }
}
class Entity {
    public boolean isOn() { return false; }
}


abstract interface h {
    g test();
}

abstract interface g {
    void accept();
}
