package org.projpi.jetSuite.items.item;


import org.projpi.jetSuite.items.attribAPI.Attribute;
import org.projpi.jetSuite.items.attribAPI.Slot;

/**
 * Description here.
 *
 * @author UberPilot
 */
public class ItemAttribute
{
    Attribute attribute;
    Slot slot;
    int operator;
    double amount;

    public ItemAttribute(String attribute, String slot, int operator, double amount)
    {
        this.attribute = Attribute.valueOf(attribute.toUpperCase());
        this.slot = Slot.valueOf(slot.toUpperCase().replaceAll(" ", "_"));
        this.operator = (operator < 3 && operator >= 0) ? operator : 0;
        this.amount = amount;
    }

    public Attribute getAttribute()
    {
        return attribute;
    }

    public Slot getSlot()
    {
        return slot;
    }

    public int getOperator()
    {
        return operator;
    }

    public double getAmount()
    {
        return amount;
    }
}
