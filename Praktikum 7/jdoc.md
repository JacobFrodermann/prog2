```java
package controller;

/**
 * A class to represent a layer on wich elements are drawn.
 *
 * <p>BOTTOM: 100 (you can de- or increase this value if you need to) <br>
 * DEFAULT: 200 (you can de- or increase this value if you need to) <br>
 * TOP: 300 (you can de- or increase this value if you need to)
 *
 * <p>ControllerLayers with a higher value are drawn last (i.e. over other entities).
 *
 * <p>You can construct a user-defined ControllerLayer with any possible integer value.
 */
public record ControllerLayer(int value) implements Comparable<ControllerLayer> {
    public static final int BOTTOM_VALUE = 100;
    public static final int DEFAULT_VALUE = 200;
    public static final int TOP_VALUE = 300;
    public static final ControllerLayer BOTTOM = new ControllerLayer(BOTTOM_VALUE);
    public static final ControllerLayer DEFAULT = new ControllerLayer(DEFAULT_VALUE);
    public static final ControllerLayer TOP = new ControllerLayer(TOP_VALUE);

    @Override
    public int compareTo(ControllerLayer o) {
        return Integer.compare(value, o.value);
    }
}
```

gut:
 - Die Klasse ist dokumentiert.
 - Die Werte für die Standard-Layer (BOTTOM, DEFAULT, TOP) werden erklärt.
 - Die Zeichenreihenfolge der Layer wird beschrieben.

nicht gut:
 - Schreibfehler: "wich" sollte "which" sein.
 - Wiederholungen bei den Standardwerten, könnten zentraler erklärt werden.
 - compareTo ist nicht dokumentiert.

```java
package controller;

/**
 * A class to represent a layer on which elements are drawn.
 *
 * <p>Each ControllerLayer has a numeric value that defines draw order.
 * Layers with higher values are drawn later and appear on top.
 *
 * <p>Standard layers:
 * BOTTOM = 100
 * DEFAULT = 200
 * TOP = 300
 *
 * <p>You can also define custom layers with any integer value.
 */
public record ControllerLayer(int value) implements Comparable<ControllerLayer> {
    public static final int BOTTOM_VALUE = 100;
    public static final int DEFAULT_VALUE = 200;
    public static final int TOP_VALUE = 300;

    public static final ControllerLayer BOTTOM = new ControllerLayer(BOTTOM_VALUE);
    public static final ControllerLayer DEFAULT = new ControllerLayer(DEFAULT_VALUE);
    public static final ControllerLayer TOP = new ControllerLayer(TOP_VALUE);

    /**
     * Compares this layer to another layer by value.
     */
    @Override
    public int compareTo(ControllerLayer o) {
        return Integer.compare(value, o.value);
    }
}

```