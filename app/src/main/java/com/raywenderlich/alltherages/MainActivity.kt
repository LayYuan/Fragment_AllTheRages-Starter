//Title: Learn Android Fragments
//Date: 2018-06-26
//Reference from https://www.raywenderlich.com/169885/android-fragments-tutorial-introduction-2
//
//Description:
//A fragment is an Android component that holds part of the behavior and/or UI of an activity.
//Fragments are tied to a single activity.
//
//Fragments lifecycle:
//Like an activity, a fragment has a lifecycle with events that occur when the fragment’s status changes.
//onAttach: When the fragment attaches to its host activity.
//onCreate: When a new fragment instance initializes,
//onCreateView: When a fragment creates its portion of the view hierarchy,
//onActivityCreated: When the fragment’s activity has finished its own onCreate event.
//onStart: When the fragment is visible; a fragment starts only after its activity starts
//onResume: When the fragment is visible and interactable; a fragment resumes only after its activity resumes
//onPause: When the fragment is no longer interactable
//onStop: When the fragment is no longer visible
//onDestroyView: When the view and related resources created in onCreateView are removed from the activity’s view hierarchy and destroyed.
//onDestroy: When the fragment does its final clean up.
//onDetach: When the fragment is detached from its activity.
//
//Advantages:
//Modularity: Dividing complex activity code across fragments for better organization and maintenance
//Reusability: Placing behavior or UI parts into fragments that can be shared across multiple activities.
//Adaptability: Representing sections of a UI as different fragments and utilizing different layouts
// depending on screen orientation and size.
//
//Learn:
//1. How to create and add fragments to an activity.
//2. How to let your fragments send information to an activity.
//3. How to add and replace fragments by using transactions.
//
//



package com.raywenderlich.alltherages

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

//class MainActivity : AppCompatActivity() {
class MainActivity : AppCompatActivity(), RageComicListFragment.OnRageComicSelected {

  /*
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }*/

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    //Adding a Fragment Dynamically
    //get RageComicListFragment into MainActivity.
    //grab the FragmentManager by referencing supportFragmentManager,
    //FragmentManager to start a new transaction by calling beginTransaction()
    if (savedInstanceState == null) {
      supportFragmentManager
              .beginTransaction()
              .add(R.id.root_layout, RageComicListFragment.newInstance(), "rageComicList")
              .commit()
    }
  }

  override fun onRageComicSelected(comic: Comic) {
    //Toast.makeText(this, "Hey, you selected " + comic.name + "!", Toast.LENGTH_SHORT).show()

    val detailsFragment =
            RageComicDetailsFragment.newInstance(comic)
    supportFragmentManager.beginTransaction()
            .replace(R.id.root_layout, detailsFragment, "rageComicDetails")
            .addToBackStack(null)
            .commit()
  }

}
