/*
 * TODO put header
 */
package eu.lighthouselabs.obd.commands.control;

import eu.lighthouselabs.obd.commands.ObdCommand;

/**
 * TODO put description
 * 
 */
public class DtcNumberObdCommand extends ObdCommand {

	private int codeCount = -1;
	private boolean milOn = false;

	/**
	 * Default ctor.
	 */
	public DtcNumberObdCommand() {
		super("01 01");
	}

	/**
	 * Copy ctor.
	 * 
	 * @param other
	 */
	public DtcNumberObdCommand(DtcNumberObdCommand other) {
		super(other);
	}

	/**
	 * 
	 */
	public String getFormattedResult() {
		String res = getResult();
		
		if (!"NODATA".equals(res)) {
			// ignore first two bytes [hh hh] of the response
			byte mil = buff.get(2);
			if ((mil & 0x80) == 1)
				milOn = true;
			
			codeCount = mil & 0x7f;
		}

		res = milOn ? "MIL is ON" : "MIL is OFF";
		
		return new StringBuilder().append(res).append(codeCount).append(" codes").toString();
	}

	public int getTotalAvailableCodes() {
		return codeCount;
	}

	public boolean getMilOn() {
		return milOn;
	}

	
}