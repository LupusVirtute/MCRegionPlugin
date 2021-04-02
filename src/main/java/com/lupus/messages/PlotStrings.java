package com.lupus.messages;

import com.lupus.economy.Prices;
import com.lupus.gui.utils.TextUtility;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public class PlotStrings {
	public static String plotGUITitle = ChatColor.GREEN + "Zarzadzanie dzialka";
	public static String[] plotGUIDisplayNames =
			{
					"&9/dzialka dodaj [Gracz]",
					"&9/dzialka wyrzuc [Gracz]",
					"&9/dzialka teleport [nazwa dzialki]",
					"&9/dzialka ulepsz",
					"&9/dzialka stats [dzialka]",
					"&3/dzialka granica",
					"&9/dzialka usun",
					"&3/dzialka nazwa [nowa nazwa]",
			};
	public static String[][] lores =
			{
					{"&9&lDodaj tą komendą graczy do swojej działki"},
					{"&9&lUsuń tą komendą graczy z swojej działki"},
					{"&9Teleportujesz się do wyznaczonej ","przez ciebie działki"},
					{"&eUlepszasz działke", ChatColor.YELLOW + "aby to zrobić musisz mieć "+ Prices.regionUpgrade+" $ x lvl działki"},
					{"&eOglądasz statystyki działki", ChatColor.DARK_RED + "wpisz nazwe działki żeby podglądać innych!"},
					{ChatColor.DARK_GREEN + "Pokazuje granice działki","w formie granicy", ChatColor.DARK_GREEN + "Stworzonej z niebieskich cząsteczek!!"},
					{"&4Usuwasz działke musisz", ChatColor.DARK_RED + "użyć podwójnie komendy by zadziałała"},
					{TextUtility.color("&eMożesz zmienić"),TextUtility.color("&cnazwe działki na inną!")},
			};
	public static String createPlotDisplayName = ChatColor.translateAlternateColorCodes('&', "&9/dzialka stworz [nazwa]");
	@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
	public static List<String> createPlotLore = Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&eTworzysz nową działkę"));

}
