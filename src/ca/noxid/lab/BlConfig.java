package ca.noxid.lab;

import ca.noxid.lab.gameinfo.GameInfo;
import com.carrotlord.string.StrTools;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BlConfig {
	private int lineResolution = 4;
	private int entityResolution = 16;
	private int tileSize = 16;
	private int tilesetWidth = 16;
	private int mapMinX = 21;
	private int mapMinY = 16;
	private boolean useScriptSource = true;
	private String tilesetPrefix = "Prt";
	private String npcPrefix = "Npc";
	private String backgroundPrefix = "bk";
	private int gradientLayerAlpha = 50;
	private String encoding = "UTF-8";

	private static final String[] fluff = {
			" - Line resolution",
			" - Entity Resolution",
			" - Tile Size",
			" - Tileset Width",
			" - Map min x",
			" - Map min y",
			" - Use ScriptSource files",
			" - Identifies file as a tileset",
			" - Identifies file as an NPC sheet",
			" - Identifies file as a background",
			" - Used for the alpha slider",
			" - Character encoding",
	};

	private File configFile;

	public int getTileSize() {
		return tileSize;
	}

	public int getEntityRes() {
		return entityResolution;
	}

	public int getLineRes() {
		return lineResolution;
	}

	public int getTilesetWidth() {
		return tilesetWidth;
	}

	public int getMapMinX() {
		return mapMinX;
	}

	public int getMapMinY() {
		return mapMinY;
	}

	public boolean getUseScriptSource() {
		return useScriptSource;
	}

	public String getTilesetPrefix() {
		return tilesetPrefix;
	}

	public String getNpcPrefix() {
		return npcPrefix;
	}

	public String getBackgroundPrefix() {
		return backgroundPrefix;
	}

	public int getGradientAlpha() {
		return gradientLayerAlpha;
	}

	public void setGradientAlpha(int val) {
		if (val < 0) val = 0;
		if (val > 100) val = 100;
		gradientLayerAlpha = val;
	}

	public String getEncoding() {
		return encoding;
	}

	public void set(String[] vals) {
		if (vals.length >= 6) {
			lineResolution = Integer.parseInt(vals[0]);
			entityResolution = Integer.parseInt(vals[1]);
			tileSize = Integer.parseInt(vals[2]);
			tilesetWidth = Integer.parseInt(vals[3]);
			mapMinX = Integer.parseInt(vals[4]);
			mapMinY = Integer.parseInt(vals[5]);

		}
		if (vals.length >= 10) {
			useScriptSource = Boolean.parseBoolean(vals[6]);
			tilesetPrefix = vals[7];
			npcPrefix = vals[8];
			backgroundPrefix = vals[9];
		}
		if (vals.length >= 11) {
			gradientLayerAlpha = Integer.parseInt(vals[10]);
		}
		if (vals.length >= 12) {
			encoding = vals[11];
		}
	}

	public BlConfig(File configFile, GameInfo.MOD_TYPE type) {
		this.configFile = configFile;
		if (type == GameInfo.MOD_TYPE.MOD_CS) {
			tileSize = 16;
		}
		if (configFile.exists()) {
			Scanner sc = null;
			try {
				sc = new Scanner(configFile);
				lineResolution = sc.nextInt();
				sc.nextLine();
				entityResolution = sc.nextInt();
				sc.nextLine();
				tileSize = sc.nextInt();
				sc.nextLine();
				tilesetWidth = sc.nextInt();
				sc.nextLine();
				mapMinX = sc.nextInt();
				sc.nextLine();
				mapMinY = sc.nextInt();
				sc.nextLine();
				useScriptSource = sc.nextBoolean();
				sc.nextLine();
				tilesetPrefix = sc.next();
				sc.nextLine();
				npcPrefix = sc.next();
				sc.nextLine();
				backgroundPrefix = sc.next();
				sc.nextLine();
				gradientLayerAlpha = sc.nextInt();
				sc.nextLine();
				encoding = sc.next();
				sc.nextLine();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchElementException e) {
				//yolo
			} finally {
				if (sc != null) {
					sc.close();
				}
			}
		}
	}

	public void save() {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(configFile));
			Object[] vals = {
					lineResolution, entityResolution, tileSize, tilesetWidth,
					mapMinX, mapMinY, useScriptSource, tilesetPrefix, npcPrefix, backgroundPrefix,
					gradientLayerAlpha, encoding};
			for (int i = 0; i < vals.length; i++) {
				out.write(vals[i] + fluff[i] + "\r\n");
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StrTools.msgBox("Error writing to " + configFile + " !");
		}

	}
}
