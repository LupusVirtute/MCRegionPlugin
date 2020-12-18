package com.lupus.messages;

import com.lupus.region.Region;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public enum PlotMessages {
	PLAYER_OFFLINE(ChatColor.RED  + "Gracz jest offline"),
	PLOT_ALERADY_EXISTS(ChatColor.RED + "Działka już o takiej nazwie istnieje"),
	PLAYER_ONLY(ChatColor.RED+ "Te komendy są tylko dla graczy"),
	SUCCESSFUL_LEAVE(ChatColor.RED + "Pomyślnie opuściłeś działke %string%"),
	PLAYER_LEFT(ChatColor.RED + "Gracz %string% opuścił twoją działke!"),
	NO_PLOT(ChatColor.RED + "Nie posiadasz działki"),
	NOT_INSIDE_REGION(ChatColor.RED + "Nie jesteś na działce"),
	CHANGED_SPAWN_SUCCESFULLY(ChatColor.GREEN + "Udało ci się zmienić spawn działki"),
	NO_INVITE(ChatColor.RED + "Nie masz żadnego zaproszenia"),
	NULL_PLOT(ChatColor.RED + "Działka nie istnieje"),
	NO_BELONG(ChatColor.RED + "Nie jesteś przypisany do tej działki"),
	SOMETHING_WENT_WRONG(ChatColor.RED + "Coś poszło nie tak spróbuj jeszcze raz"),
	MAX_LEVEL(ChatColor.RED + "Działka jest na najwyższym możliwym poziomie to jest poziom 9"),
	MAX_LEVEL_FOR_PLAYER( ChatColor.RED + "Działka jest już na najwyższym poziomie dla gracza tylko vip może dojść do 8 poziomu sprawdź "+ChatColor.GOLD+"/vip"),
	ALERADY_OWNS_PLOT(ChatColor.RED + "Już posiadasz działke"),
	REGION_LOCATION_INVALID(ChatColor.RED + "Jesteś za blisko regionu administracyjnego lub jesteś za blisko innej działki oddal się o 98 bloków"),
	INVALID_INTERACT(ChatColor.translateAlternateColorCodes('&', "&f[&a&lTOC&r] &4Nie możesz interaktować z przedmiotami na czyjejś działce")),
	INVALID_DAMAGE(ChatColor.translateAlternateColorCodes('&', "&f[&a&lTOC&r] &4Nie możesz bić zwierząt na czyjejś działce")),
	ACCEPT_INVITE(ChatColor.GREEN + "Zaakceptowałeś zaproszenie od %string%"),
	DENY_INVITE(ChatColor.RED + "Odrzuciłeś zaproszenie od %string%"),
	OWNER_ACCEPT_INVITE_MESSAGE(ChatColor.GREEN + "%string% Zaakceptował zaproszenie od ciebie do twojej działki"),
	CANT_USE_ON_MYSELF(ChatColor.RED + "Nie możesz użyć tej komendy na sobie"),
	INVITE_SEND(
			ChatColor.YELLOW + "Dostałeś zaproszenie od gracza %string%\n"+
			ChatColor.DARK_GRAY + "Użyj komendy: /dzialka "
					+ ChatColor.DARK_GREEN + "akceptuj" + ChatColor.DARK_GRAY + "/" + ChatColor.DARK_RED + "odrzuc"),
	INVITE_SUCCESFUL(ChatColor.GREEN + "Wysłano zaproszenie do gracza %string%"),
	SUCCESFUL_CREATION(ChatColor.GREEN + "Działka pomyślnie stworzona o nazwie %string%"),
	OWNER_DENY_INVITE_MESSAGE(ChatColor.RED + "%string% Odrzucił zaproszenie od ciebie do twojej działki"),
	SHOWING_BORDER(ChatColor.GREEN + "Zaczęto pokazywać granice działki %string%"),
	SUCCESSFUL_REMOVE(ChatColor.GREEN + "Pomyślnie usunąłeś gracza %string%"),
	SUCCESSFUL_UPGRADE(ChatColor.GREEN + "Udało ci się poprawnie powiększyć działke aktualny poziom: " +ChatColor.YELLOW+ "%string%/9"),
	DELETE_CONFIRMATION(ChatColor.RED + "Czy na pewno chcesz usunąć działke o nazwie " +ChatColor.GOLD + "%string%" + ChatColor.RED + " jeśli tak to potwierdź wpisując jeszcze raz tą komendę");

	private final String message;
	PlotMessages(String message){
		this.message = message;
	}
	@Override
	public String toString(){
		return this.message;
	}
	public String toString(Player player){
		return this.message.replace("%string%",player.getName());
	}
	public String toString(String string){
		return this.message.replace("%string%",string);
	}
	public String toString(Region r) {
		return this.message.replace("%string%",r.getName());
	}
}
