package com.vlad1m1r.baselibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Patterns;

import com.vlad1m1r.baselibrary.R;


/**
 * Created by vladimirjovanovic on 10/21/15.
 */
public class ActionUtils {

    public enum ActionType {
        WEB,
        EMAIL,
        CALL,
        SHARE,
        EXTERNAL,
        PLAY_STORE
    }

    public static void doAction(@NonNull final Context context, @NonNull String mainData) {
        if (mainData.startsWith("mailto:")) {
            mainData = mainData.replaceAll("mailto:", "");

            if (Patterns.EMAIL_ADDRESS.matcher(mainData).matches()) {
                doAction(context, ActionType.EMAIL, mainData);
            }

        } else if (Patterns.EMAIL_ADDRESS.matcher(mainData).matches()) {
            doAction(context, ActionType.EMAIL, mainData);
        } else if (Patterns.WEB_URL.matcher(mainData).matches()) {
            doAction(context, ActionType.WEB, mainData);
        } else if (Patterns.PHONE.matcher(mainData).matches()) {
            doAction(context, ActionType.CALL, mainData);
        }
    }

    public static void doAction(Context context, ActionType type, String mainData) {
        switch (type) {
            case WEB: {
                if (mainData.startsWith("www")) {
                    mainData = "http://" + mainData;
                }
                if (Patterns.WEB_URL.matcher(mainData).matches()) {

//                    CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
//
//                    intentBuilder.setToolbarColor(context.getResources().getColor(R.color.color_primary));
//                    intentBuilder.setShowTitle(true);
//                    intentBuilder.setStartAnimations(context,
//                            R.anim.push_left_in, R.anim.fadeout);
//                    intentBuilder.setExitAnimations(context,
//                            R.anim.fadein, R.anim.push_right_out);
//
//
//                    Bitmap mCloseButtonBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_navigation_arrow_back);
//                    intentBuilder.setCloseButtonIcon(mCloseButtonBitmap);
//
//                    Uri uri = Uri.parse(mainData);
//
//                    CustomTabActivityHelper.openCustomTab(context, intentBuilder.build(), uri, null);

                }
                break;
            }
            case EMAIL: { //email
                if (Patterns.EMAIL_ADDRESS.matcher(mainData).matches()
                        || (mainData.startsWith("mailto:") && Patterns.EMAIL_ADDRESS.matcher(mainData.substring(7)).matches())) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{mainData});
                    context.startActivity(Intent.createChooser(intent, ""));
                }
                break;
            }
            case CALL: { //phone
                if (Patterns.PHONE.matcher(mainData).matches()) {
                    Intent i = new Intent(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:" + mainData));
                    context.startActivity(Intent.createChooser(i, context.getResources().getString(R.string.action__call_number)));
                }
                break;
            }
            case SHARE: {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, mainData);
                context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.action__share_via)));
                break;
            }
            case EXTERNAL: {
                if (Patterns.WEB_URL.matcher(mainData).matches()) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mainData));
                    context.startActivity(browserIntent);
                }
                break;
            }
            case PLAY_STORE: {
                if (mainData.startsWith("market:")) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mainData));
                    context.startActivity(browserIntent);
                }
                break;
            }
            default: {

            }
        }
    }
}
