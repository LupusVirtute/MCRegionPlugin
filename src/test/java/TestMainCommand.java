import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.commands.PlotMainSupCommand;
import com.lupus.managers.InviteManager;
import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import com.lupus.utils.ColorUtil;
import org.bukkit.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


// There's need for only testing if it's successful execution of CMD if i test every situation i will never complete this plugin XD

public class TestMainCommand extends TestAbstractClass {
	LupusCommand lupusCommand = new PlotMainSupCommand();
	/*
	DUE TO MOCK BUKKIT WE CANT TEST SKULLS
	@Test
	void TestMainCommand_OpenUpPanel_Success(){
		Player player = mock.addPlayer();
		Location loc = new Location(WorldUtils.getMainWorld(),0.0,0.0,0.0);
		player.teleport(loc);
		lupusCommand.run(player, new String[0]);
		InventoryView view = player.getOpenInventory();
		Inventory inv = view.getTopInventory();
		for (int i=0;i<6;i++)
			assertEquals(Material.SKULL, inv.getItem(i).getType());
	}
	 */
	@Test
	void TestCreatePlotCommand_PlayerCreates_Success(){
		createNonNullRegion();
	}
	@Test
	void TestDeletePlotCommand_PlayerDeletes_Success(){
		lupusCommand.run(owner,new String[]{
				"usun"
		});
		lupusCommand.run(owner,new String[]{
				"usun"
		});
		Region r = RegionManager.getRegionOfOwner(owner);
		assertNull(r);
	}

	@Test
	void TestSubCommandAddToPlotCMDAcceptInvite_InvitePlayerAndHeAccepts_Success(){
		Region r = createNonNullRegion();
		lupusCommand.run(owner,new String[]{
				"dodaj",
				invitee.getName()
		});
		assertNotNull(InviteManager.getInvite(invitee));

		lupusCommand.run(invitee,new String[]{
				"akceptuj"
		});
		assertInRegion(r);
	}
	@Test
	void TestSubCommandAddToPlotCMDDeclineInvite_InvitePlayerAndHeDeclines_Success(){
		Region r = createNonNullRegion();
		lupusCommand.run(owner,new String[]{
				"dodaj",
				invitee.getName()
		});
		assertNotNull(InviteManager.getInvite(invitee));

		lupusCommand.run(invitee,new String[]{
				"odrzuc"
		});
		assertNotInRegion(r);
	}
	@Test
	void TestChangePlotNameCMD_ChangeSuccesfully_Success(){
		Region r = createNonNullRegion();
		String name = r.getName();
		assertEquals(ColorUtil.strip(name),"TEST"+(n-1));
		lupusCommand.run(owner,new String[]{
				"nazwa",
				"TEST"+n
		});
		n++;
		assertNotEquals(name,r.getName());
	}
	@Test
	void TestLeaveCMD_LeaveOwnerRegion_Success(){
		Region r = createNonNullRegion();
		forceAdd();
		lupusCommand.run(invitee,new String[]{
			"opusc",
			r.getName()
		});
		assertNotInRegion(r);
	}
	@Test
	void TestThrowOutCMD_ThrownOut_Success(){
		Region r = createNonNullRegion();
		forceAdd();
		lupusCommand.run(owner,new String[]{
			"wyrzuc",
			invitee.getName()
		});
		assertNotInRegion(r);
	}
	@Test
	void TestSetSpawn_SetNewSpawnNearCenter_Success(){
		Region r = createNonNullRegion();
		Location spawn = r.getSpawn();
		owner.teleport(spawn.clone().add(6,6,6));
		lupusCommand.run(owner,new String[]{
			"setspawn"
		});
		assertNotEquals(spawn,r.getSpawn());
		assertEquals(spawn.add(6,6,6),r.getSpawn());
	}

}
