package com.reactnativenavigation.parse;

import android.support.annotation.ColorInt;
import android.view.MenuItem;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.reactnativenavigation.parse.NavigationOptions.NO_VALUE;
import static com.reactnativenavigation.parse.NavigationOptions.NO_INT_VALUE;
import static com.reactnativenavigation.parse.NavigationOptions.NO_FLOAT_VALUE;
import static com.reactnativenavigation.parse.NavigationOptions.NO_COLOR_VALUE;

public class Button {
	public String id;
	public String title;
	public NavigationOptions.BooleanOptions disabled;
	public NavigationOptions.BooleanOptions disableIconTint;
	public int showAsAction;
	@ColorInt
	public int buttonColor;
	public int buttonFontSize;
	public String buttonFontWeight;
	public String icon;

	public static Button parseJson(JSONObject json)  {
		Button button = new Button();
		button.id = json.optString("id");
		button.title = json.optString("title", NO_VALUE);
		button.disabled = NavigationOptions.BooleanOptions.parse(json.optString("disabled", NO_VALUE));
		button.disableIconTint = NavigationOptions.BooleanOptions.parse(json.optString("disableIconTint", NO_VALUE));
		button.showAsAction = parseShowAsAction(json.optString("showAsAction", NO_VALUE));
		button.buttonColor = json.optInt("buttonColor", NO_INT_VALUE);
		button.buttonFontSize = json.optInt("buttonFontSize", NO_INT_VALUE);
		button.buttonFontWeight = json.optString("buttonFontWeight", NO_VALUE);

		JSONObject iconObject = json.optJSONObject("icon");
		if (iconObject != null) {
			button.icon = iconObject.optString("uri", NO_VALUE);
		}

		return button;
	}

	public static ArrayList<Button> parseJsonArray(JSONArray jsonArray) {
		ArrayList<Button> buttons = new ArrayList<>();

		if (jsonArray == null) {
			return null;
		}

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject json = jsonArray.optJSONObject(i);
			Button button =	Button.parseJson(json);
			buttons.add(button);
		}

		return buttons;
	}

	private static int parseShowAsAction(String showAsAction) {
		if (NO_VALUE.equals(showAsAction)) {
			return MenuItem.SHOW_AS_ACTION_IF_ROOM;
		}

		switch (showAsAction) {
			case "always":
				return MenuItem.SHOW_AS_ACTION_ALWAYS;
			case "never":
				return MenuItem.SHOW_AS_ACTION_NEVER;
			case "withText":
				return MenuItem.SHOW_AS_ACTION_WITH_TEXT;
			case "ifRoom":
			default:
				return MenuItem.SHOW_AS_ACTION_IF_ROOM;
		}
	}
}