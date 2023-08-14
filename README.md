# NavCamApp Notes

This is just a couple of things that need to be clarified and said.

  1. I did not complete making the APK. I tried, but couldn't figure
     out how to use FFmpeg in Android Studio. I tried to outline kind
     of how it would go, but realistically I don't know how helpful that
     was considering I am still fairly unfamiliar with Java.

  2. You will notice that there are a lot of lines commented out and that
     was due to getting the project to build and cause I didn't know how
     to fix them so that they weren't causing problems.

  3. I heavily referenced the CPU Monitor app to figure out how to set up
     the ROS Service and the main service as well. I left the GS Service in
     there because I had intended to test my APK using the GS manager and the
     simulation. I had very mixed results with that, nothing good. Ultimately,
     due to my own inexperience and time constraints, the GS Service is also
     incomplete, but I tried. Theoretically, the idea was to take the images
     provided by the simulation and make them into a video, but I didn't get
     to that point.

  4. Also to get the project to build I had to delete some files and change
     some things. For instance, to match with the CPU Monitor I changed a lot
     in the gradle files and deleted the themes package under the res package.
     The changes in the gradle file can be seen by what worked and what was originally
     there, I made note by commenting it.

Hopefully, what I managed to do was remotely helpful. If it wasn't I'm sorry,
but regardless, I still learned quite a bit from this experience. Again, thank 
you to those who gave me this amazing opportunity and helped me throughout the process. 

Also if the library that I used is not something helpful or that cannot
be used for whatever reason, here is a list of additional options:

  * WritingMinds: https://github.com/WritingMinds/ffmpeg-android (GPL Licensing)
  * Bravobit: https://github.com/bravobit/FFmpeg-Android (MIT Licensing)
  * yangjie10930/EpMedia: https://github.com/yangjie10930/EpMedia (MIT Licensing)
      - Note: The instructions appear to only be in Mandarin

Here is a list of alternatives as well to using FFmpeg, but I don't know how useful
they will be or if they can do what you will need them for:

  * MediaCodec Android
  * LiTr: https://github.com/linkedin/LiTr (BSD Licensing)
  * INDExOS Media for Mobile: https://github.com/INDExOS/media-for-mobile (Unknown Licensing)
