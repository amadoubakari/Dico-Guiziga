package com.kyossi.dg.fragments.behavior;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.provider.Settings;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationManagerCompat;

import com.kyossi.dg.R;
import com.kyossi.dg.architecture.core.AbstractFragment;
import com.kyossi.dg.architecture.custom.CoreState;
import com.flys.tools.dialog.MaterialNotificationDialog;
import com.flys.tools.domain.NotificationData;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_settings_layout)
@OptionsMenu(R.menu.menu_home)
public class SettingsFragment extends AbstractFragment implements MaterialNotificationDialog.NotificationButtonOnclickListeneer {

    @ViewById(R.id.notification_switch)
    protected SwitchMaterial enableNotification;

    @ViewById(R.id.notification_night_mode_switch)
    protected SwitchMaterial enabledNightMode;

    @ViewById(R.id.dialog_change_language)
    protected LinearLayout languageTv;

    @ViewById(R.id.current_language)
    protected TextView tvLanguage;

    @ViewById(R.id.radioGroup)
    protected RadioGroup radioGroup;

    @ViewById(R.id.radioButtonBlue)
    protected MaterialRadioButton radioButtonBlue;

    @ViewById(R.id.radioButtonRed)
    protected MaterialRadioButton radioButtonRed;

    @ViewById(R.id.radioButtonGreen)
    protected MaterialRadioButton radioButtonGreen;

    @ViewById(R.id.radioButtonPink)
    protected MaterialRadioButton radioButtonPink;

    @ViewById(R.id.radioButtonYellow)
    protected MaterialRadioButton radioButtonYellow;

    @ViewById(R.id.radioButtonPurple)
    protected MaterialRadioButton radioButtonPurple;

    private MaterialNotificationDialog notificationDialog;

    @Click(R.id.notification_switch)
    public void settings() {
        String msg = "";
        if (enableNotification.isChecked()) {
            msg = getString(R.string.enable_notifications_to_received_news);
        } else {
            msg = getString(R.string.disable_notifications_to_dont_received_news);
        }
        notificationDialog = new MaterialNotificationDialog(activity, new NotificationData(getString(R.string.app_name), msg, getString(R.string.button_yes_msg), getString(R.string.button_no_msg), activity.getDrawable(R.drawable.logo), R.style.customMaterialAlertEditDialog), this);
        notificationDialog.show(getActivity().getSupportFragmentManager(), "settings_notification_dialog_tag");
    }


    @Override
    public CoreState saveFragment() {
        return new CoreState();
    }

    @Override
    protected int getNumView() {
        return mainActivity.SETTINGS_FRAGMENT;
    }

    @Override
    protected void initFragment(CoreState previousState) {
        enableNotification.setChecked(NotificationManagerCompat.from(activity).areNotificationsEnabled());
    }

    @Override
    protected void initView(CoreState previousState) {
        if (isEnglish() == Language.ENGLISH.getOrder()) {
            tvLanguage.setText(getString(R.string.fragment_settings_language_en));
        } else {
            tvLanguage.setText(getString(R.string.fragment_settings_language_fr));
        }
        int nightModeFlags = activity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                enabledNightMode.setChecked(true);
                break;
            case Configuration.UI_MODE_NIGHT_NO:
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                enabledNightMode.setChecked(false);
                break;
        }
    }

    @Override
    protected void updateOnSubmit(CoreState previousState) {

    }

    @Override
    protected void updateOnRestore(CoreState previousState) {

    }

    @Override
    public void onFragmentResume() {
        super.onFragmentResume();
        loadCheckedTheme();
        radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.radioButtonBlue:
                     setCustomTheme(R.style.AppTheme);
                     break;
                case R.id.radioButtonRed:
                    setCustomTheme(R.style.AppThemeRed);
                    break;
                case R.id.radioButtonGreen:
                    setCustomTheme(R.style.AppThemeGreen);
                    break;
                case R.id.radioButtonPink:
                    setCustomTheme(R.style.AppThemePink);
                    break;
                case R.id.radioButtonYellow:
                    setCustomTheme(R.style.AppThemeYellow);
                    break;
                case R.id.radioButtonPurple:
                    setCustomTheme(R.style.AppThemePurple);
                    break;
            }
        });
    }


    private void loadCheckedTheme() {
        switch (mainActivity.getCustomTheme()) {
            case R.style.AppTheme:
                radioButtonBlue.setChecked(true);
                break;
            case R.style.AppThemeRed:
                radioButtonRed.setChecked(true);
                break;
            case R.style.AppThemeGreen:
                radioButtonGreen.setChecked(true);
                break;
            case R.style.AppThemePink:
                radioButtonPink.setChecked(true);
                break;
            case R.style.AppThemeYellow:
                radioButtonYellow.setChecked(true);
                break;
            case R.style.AppThemePurple:
                radioButtonPurple.setChecked(true);
                break;
        }
    }

    @Override
    protected void notifyEndOfUpdates() {

    }

    @Override
    protected void notifyEndOfTasks(boolean runningTasksHaveBeenCanceled) {

    }

    @Override
    protected boolean hideNavigationBottomView() {
        return false;
    }

    @Override
    public void okButtonAction(DialogInterface dialogInterface, int i) {
        Intent settingsIntent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).putExtra(Settings.EXTRA_APP_PACKAGE, getActivity().getPackageName());
        settingsActivityResultLauncher.launch(settingsIntent);
    }


    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> settingsActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            enableNotification.setChecked(NotificationManagerCompat.from(activity).areNotificationsEnabled());
        }
    });

    @Override
    public void noButtonAction(DialogInterface dialogInterface, int i) {
        enableNotification.setChecked(NotificationManagerCompat.from(activity).areNotificationsEnabled());
        dialogInterface.dismiss();
    }

    @Click(R.id.dialog_change_language)
    public void changeLanguageAction() {
        changeLanguage();
    }

    @Click(R.id.notification_night_mode_switch)
    public void switchNightModeAction() {
        if (enabledNightMode.isChecked()) {
            mainActivity.setNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            mainActivity.setNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}
