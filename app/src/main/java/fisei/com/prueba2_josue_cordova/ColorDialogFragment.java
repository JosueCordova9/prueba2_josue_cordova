// ColorDialogFragment.java
// Allows user to set the drawing color on the DoodleView
package fisei.com.prueba2_josue_cordova;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

// class for the Select Color dialog
public class ColorDialogFragment extends DialogFragment {
   private SeekBar alphaSeekBar;
   private SeekBar redSeekBar;
   private SeekBar greenSeekBar;
   private SeekBar blueSeekBar;
   private View colorView;
   protected int color;

   // create an AlertDialog and return it
   @Override
   public Dialog onCreateDialog(Bundle bundle) {
      // create dialog
      AlertDialog.Builder builder =
         new AlertDialog.Builder(getActivity());
      View colorDialogView = getActivity().getLayoutInflater().inflate(
         R.layout.fragment_color, null);
      builder.setView(colorDialogView); // add GUI to dialog

      // set the AlertDialog's message
      builder.setTitle(getTitle());

      // get the color SeekBars and set their onChange listeners
      alphaSeekBar = (SeekBar) colorDialogView.findViewById(
         R.id.alphaSeekBar);
      redSeekBar = (SeekBar) colorDialogView.findViewById(
         R.id.redSeekBar);
      greenSeekBar = (SeekBar) colorDialogView.findViewById(
         R.id.greenSeekBar);
      blueSeekBar = (SeekBar) colorDialogView.findViewById(
         R.id.blueSeekBar);
      colorView = colorDialogView.findViewById(R.id.colorView);

      // register SeekBar event listeners
      alphaSeekBar.setOnSeekBarChangeListener(colorChangedListener);
      redSeekBar.setOnSeekBarChangeListener(colorChangedListener);
      greenSeekBar.setOnSeekBarChangeListener(colorChangedListener);
      blueSeekBar.setOnSeekBarChangeListener(colorChangedListener);

      // use current drawing color to set SeekBar values
      final fisei.com.prueba2_josue_cordova.DoodleView doodleView = getDoodleFragment().getDoodleView();
      color = getColorDoodleView(doodleView);
      alphaSeekBar.setProgress(Color.alpha(color));
      redSeekBar.setProgress(Color.red(color));
      greenSeekBar.setProgress(Color.green(color));
      blueSeekBar.setProgress(Color.blue(color));

      // add Set Color Button
      builder.setPositiveButton(R.string.button_set_color,
         new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
               setColorDoodleView(doodleView);
            }
         }
      );

      return builder.create(); // return dialog
   }

   protected int getColorDoodleView(fisei.com.prueba2_josue_cordova.DoodleView doodleView) {
      return doodleView.getDrawingColor();
   }

   protected void setColorDoodleView(fisei.com.prueba2_josue_cordova.DoodleView doodleView) {
      doodleView.setDrawingColor(color);
   }

   protected int getTitle() {
      return R.string.title_color_dialog;
   }

   // gets a reference to the MainActivityFragment
   private fisei.com.prueba2_josue_cordova.MainActivityFragment getDoodleFragment() {
      return (fisei.com.prueba2_josue_cordova.MainActivityFragment) getFragmentManager().findFragmentById(
         R.id.doodleFragment);
   }

   // tell MainActivityFragment that dialog is now displayed
   @Override
   public void onAttach(Activity activity) {
      super.onAttach(activity);
      fisei.com.prueba2_josue_cordova.MainActivityFragment fragment = getDoodleFragment();

      if (fragment != null)
         fragment.setDialogOnScreen(true);
   }

   // tell MainActivityFragment that dialog is no longer displayed
   @Override
   public void onDetach() {
      super.onDetach();
      fisei.com.prueba2_josue_cordova.MainActivityFragment fragment = getDoodleFragment();

      if (fragment != null)
         fragment.setDialogOnScreen(false);
   }

   // OnSeekBarChangeListener for the SeekBars in the color dialog
   private final OnSeekBarChangeListener colorChangedListener =
      new OnSeekBarChangeListener() {
         // display the updated color
         @Override
         public void onProgressChanged(SeekBar seekBar, int progress,
            boolean fromUser) {

            if (fromUser) // user, not program, changed SeekBar progress
               color = Color.argb(alphaSeekBar.getProgress(),
                  redSeekBar.getProgress(), greenSeekBar.getProgress(),
                  blueSeekBar.getProgress());
            colorView.setBackgroundColor(color);
         }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {} // required

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {} // required
      };
}
