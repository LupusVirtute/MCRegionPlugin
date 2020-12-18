import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import com.lupus.command.framework.commands.ILupusCommand;
import com.lupus.commands.PlotMainSupCommand;
import com.lupus.managers.RegionManager;
import com.lupus.region.Region;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public abstract class TestAbstractClass {
	static void initMock(){
		mock = MockBukkit.mock();
		MockBukkit.createMockPlugin();
		mainWorld = mock.getWorld("world") == null ? mock.addSimpleWorld("world") : mainWorld;
	}
	static int n=0;
	@BeforeEach
	void beforeEach(){
		owner = mock.addPlayer();
		invitee = mock.addPlayer();
	}
	public static ServerMock mock;
	public static World mainWorld;
	public static Player owner;
	public static Player invitee;
	@BeforeAll
	static void init(){
		initMock();
	}
	@AfterAll
	static void destroy(){
		RegionManager.clear();
	}
	ILupusCommand plotMainSupCMD = new PlotMainSupCommand();
	Region createNonNullRegion(){
		Location location = owner.getLocation().add(200*n,0,200*n);
		owner.teleport(location);
		plotMainSupCMD.run(owner,new String[]{
				"stworz",
				"TEST"+n
		});
		n++;
		Region r = RegionManager.getRegionOfOwner(owner);
		assertNotNull(r);
		return r;
	}
	void forceAdd(){
		plotMainSupCMD.run(owner,new String[]{
				"dodaj",
				invitee.getName()
		});
		plotMainSupCMD.run(invitee,new String[]{
				"akceptuj"
		});
		Region r = RegionManager.getRegionOfOwner(owner);
		assertInRegion(r);
	}
	void assertInRegion(@NotNull Region r){
		assertNotNull(r.getMember(invitee));
		assertTrue(RegionManager.getPlayerMemberships(invitee).contains(r.getUniqueId()));
	}
	void assertNotInRegion(@NotNull Region r){
		assertNull(r.getMember(invitee));
		assertFalse(RegionManager.getPlayerMemberships(invitee).contains(r.getUniqueId()));
	}
}
