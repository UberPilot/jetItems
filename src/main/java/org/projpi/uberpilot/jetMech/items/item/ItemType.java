package org.projpi.uberpilot.jetMech.items.item;

/**
 * Description here.
 *
 * @author UberPilot
 */
public enum ItemType
{
    NONE(""),
    INGREDIENT("Ingredient"),
    COMPONENT("Component"),
    WEAPON("Weapon"),
    TOOL("Tool"),
    ARMOR("Armor"),
    MATERIAL("Material");


    private final String name;
    ItemType(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
