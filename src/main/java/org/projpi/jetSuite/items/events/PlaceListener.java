package org.projpi.jetSuite.items.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.projpi.jetSuite.items.JetItems;

/**
 * Description here.
 *
 * @author UberPilot
 */
public class PlaceListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlace(BlockPlaceEvent e)
    {
        if(JetItems.getPlugin().getCreator().isJetItem(e.getItemInHand()))
        {
            e.setCancelled(true);
        }
    }
}
