import com.lupus.command.framework.commands.LupusCommand;
import com.lupus.commands.PlotsMainCMD;
import com.lupus.region.Region;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestDevCommand extends TestAbstractClass{
	LupusCommand lupusCommand = new PlotsMainCMD();
	@Test
	void TestChangeOwnerCMD_ChangedOwner_Success(){
		Region r = createNonNullRegion();
		lupusCommand.run(owner,new String[]{
			"changeowner",
			invitee.getName(),
			r.getName()
		});
		assertNotEquals(r.getOwnerUUID(),owner.getUniqueId());
		assertEquals(r.getOwnerUUID(),invitee.getUniqueId());
	}
	@Test
	void TestForceAddCMD_AddedToPlot_Success(){
		Region r = createNonNullRegion();
		invitee.setOp(true);
		assertNotInRegion(r);
		lupusCommand.run(invitee,new String[]{
			"forceadd",
			invitee.getName(),
			r.getName()
		});
		assertInRegion(r);
	}
	@Test
	void TestGoToCMD_GoneToPlot_Success(){
		Region r = createNonNullRegion();
		invitee.setOp(true);
		assertNotEquals(r.getSpawn(),invitee.getLocation());
		lupusCommand.run(invitee,new String[]{
			"goto",
			r.getName()
		});
		assertEquals(r.getSpawn(),invitee.getLocation());
	}/*
	@Test
	void TestRemovePlot_RemovedPlot_Success(){
		Region r = createNonNullRegion();
		lupusCommand.run(invitee,new String[]{
				"remove",
				r.getName()
		});
		r = RegionManager.findRegion(r.getUniqueId());
		assertNull(r);
	}*/
}
