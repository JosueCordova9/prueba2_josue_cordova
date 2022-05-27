// EraseImageDialogFragment.java
// Allows user to erase image
package fisei.com.prueba2_josue_cordova;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

// class for the Erase Image dialog
public class EraseImageDialogFragment extends DialogFragment {
   // create an AlertDialog and return it
   @Override
   public Dialog onCreateDialog(Bundle bundle) {
      AlertDialog.Builder builder =
         new AlertDialog.Builder(getActivity());

      // set the AlertDialog's message
      builder.setMessage(R.string.message_erase);

      // add Erase Button
      builder.setPositiveButton(R.string.button_erase,
         new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
               getDoodleFragment().getDoodleView().clear(); // clear image
            }
         }
      );

      // add cancel Button
      builder.setNegativeButton(android.R.string.cancel, null);
      return builder.create(); // return dialog
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
}
