
//Creating fragment
//The third parameter of inflate specifies whether the inflated fragment should be added to the container.
//The container is the parent view that will hold the fragment’s view hierarchy.
//You should always set this to false.
//the FragmentManager will take care of adding the fragment to the container.
//Each activity has a FragmentManager that manages its fragments.
//It also provides an interface for you to access, add and remove those fragments.
//First, not define any constructors, the compiler automatically generates an empty,default constructor that takes no arguments.
//Second, you probably know that Android may destroy and later re-create an activity and all its
// associated fragments when the app goes into the background. When the activity comes back,
// its FragmentManager starts re-creating fragments by using the empty default constructor.
// If it cannot find one, you get an exception.


package com.raywenderlich.alltherages

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raywenderlich.alltherages.databinding.FragmentRageComicDetailsBinding
import java.io.Serializable

//1 Declares RageComicDetailsFragment as a subclass of Fragment.
class RageComicDetailsFragment : Fragment() {

  //2 Provides a method for creating new instances of the fragment, a factory method.
  companion object {

    //fun newInstance(): RageComicDetailsFragment {
      //return RageComicDetailsFragment()
    //}

    private const val COMIC = "comic"

    fun newInstance(comic: Comic): RageComicDetailsFragment {
      val args = Bundle()
      args.putSerializable(COMIC, comic as Serializable)
      val fragment = RageComicDetailsFragment()
      fragment.arguments = args
      return fragment
    }


  }

  //3 Creates the view hierarchy controlled by the fragment.
  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {

    //to create the hierarchy of RageComicDetailsFragment.
    //ate(R.layout.fragment_rage_comic_details, container, false)

    val fragmentRageComicDetailsBinding = FragmentRageComicDetailsBinding.inflate(inflater!!,
            container, false)

    val comic = arguments.getSerializable(COMIC) as Comic
    fragmentRageComicDetailsBinding.comic = comic
    comic.text = String.format(getString(R.string.description_format), comic.description, comic.url)
    return fragmentRageComicDetailsBinding.root
  }

}
