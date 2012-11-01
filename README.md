GoogleTV-PIP
============

<p>This Google TV app demonstrates how to do embed live TV PIP. </p>

<p>During Google I/O 2012, one of the Google TV engineers <a href="https://www.youtube.com/watch?feature=player_embedded&v=bNbKpUqkOso">explained how to do PIP video</a> (jumpt to 56:30). 
You can use com.google.tv.mediadevices.MediaDeviceView to access the live TV video. 
Doing a Google search provided another bit of information: you need the com.google.tv.mediadevices.permission.MEDIA_DEVICES_CLIENT permission for this to work.</p>

<p>I managed to get the PIP video working by using <a href="http://developer.android.com/reference/android/widget/VideoView.html">android.widget.VideoView</a> and using 'hdmi://localhost?port=2' as the video URI.</p>

<p>Limitations:
<ul>
<li>This approach is not documented by Google and therefore not supported, but it is a good way to prototype design ideas for Google TV apps.</li>
<li>The PIP video cannot be started while the live TV app is active, so starting of the video is delayed to let the live TV app release its resources.</li>
</ul>
</p>

<p>References:
<ul>
<li><a href="https://developers.google.com/tv/">Developing for Google TV</a></li>
<li><a href="https://developers.google.com/tv/android/docs/gtv_media">Google TV Media Playback</a></li>
</ul>
</p>