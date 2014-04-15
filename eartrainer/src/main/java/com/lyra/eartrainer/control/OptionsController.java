package com.lyra.eartrainer.control;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
//import android.widget.Spinner;


import android.widget.SeekBar;
import android.widget.TextView;

import com.edmodo.rangebar.RangeBar;
import com.lyra.eartrainer.GameActivity;
import com.lyra.eartrainer.OptionsActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.R.raw;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.model.globals.Difficulties;
import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.globals.Modes;
import com.lyra.eartrainer.model.globals.ScaleTypes;
import com.lyra.eartrainer.model.instrument.IMusicInstrument;
import com.lyra.eartrainer.model.instrument.MusicInstrumentFactory;
//import com.lyra.eartrainer.model.globals.ScaleTypes;
//import com.lyra.eartrainer.model.instrument.IMusicInstrument;
import com.lyra.eartrainer.model.instrument.SoundInfo;
import com.lyra.eartrainer.properties.LyraProps;
//import com.lyra.eartrainer.model.instrument.MusicInstrumentFactory;
import com.lyra.eartrainer.view.Options;

public class OptionsController extends Controller {
	private Options optionsView;
	private RadioGroup gameMode;
	// private Spinner difficulty;
	// private Spinner scale;
	private RadioGroup instrument;
	private RangeBar interval;
	private RadioGroup direction;
	private GamePlay game;
	private TextView leftIndexValue;
	private TextView rightIndexValue;

	public OptionsController(OptionsActivity optionsActivity) {
		super(optionsActivity);
	}

	public void initialize() {
		activity.setContentView(R.layout.options);
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

		optionsView = new Options(activity);
		game = GamePlay.instance();
		game.reset();
		gameMode = (RadioGroup) activity.findViewById(R.id.game_mode_group);
		instrument = (RadioGroup) activity.findViewById(R.id.instrument_group);
		interval = (RangeBar) activity.findViewById(R.id.intervalBar);
		direction = (RadioGroup) activity.findViewById(R.id.direction_group);
		leftIndexValue = (TextView) activity.findViewById(R.id.leftInterval);
		rightIndexValue = (TextView) activity.findViewById(R.id.rightInterval);
		
//		difficulty = (Spinner) activity.findViewById(R.id.difficultySpinner);
//		scale = (Spinner) activity.findViewById(R.id.scaleSpinner);
		attachEvents();
	}

	private void attachEvents() {
		// adding button click handlers
		Button start = (Button) activity.findViewById(R.id.startGame);
		start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				storeOptions();
				goToGame();
			}
		});
		
		//TODO change this to display interval string, not index
		leftIndexValue.setText("" + interval.getLeftIndex());
		rightIndexValue.setText("" + interval.getRightIndex());
		
		// Sets the display values of the indices
        interval.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onIndexChangeListener(RangeBar rangeBar, int leftThumbIndex, int rightThumbIndex) {
            	//TODO change this to display interval string, not index
                leftIndexValue.setText("" + leftThumbIndex);
                rightIndexValue.setText("" + rightThumbIndex);
            }
        });
    	
	}

	public void goToGame() {
		Intent intent = new Intent(activity, GameActivity.class);
		activity.startActivity(intent);
		activity.finish();
	}

	public void storeOptions(){
    	
    	// choosing instrument
		int instrId = instrument.getCheckedRadioButtonId();
		RadioButton instrBtn = (RadioButton) activity.findViewById(instrId);
		int instr = instrument.indexOfChild(instrBtn);
		
		switch(instr){
		case 0:
			game.setInstrumentType(InstrumentTypes.PIANO);
			break;
		case 1:
			game.setInstrumentType(InstrumentTypes.GUITAR);
		}
    	
		// TODO change settings in gameplay instance
    	
    	// choosing difficulty
    	/*if (((String)difficulty.getSelectedItem()).equals("Beginner"))
    	{
    		game.setDifficulty(Difficulties.BEGINNER);
    	}
    	else if (((String)difficulty.getSelectedItem()).equals("Intermediate"))
    	{
    		game.setDifficulty(Difficulties.INTERMEDIATE);
    	}
    	else if (((String)difficulty.getSelectedItem()).equals("Advanced"))
    	{
    		game.setDifficulty(Difficulties.ADVANCED);
    	}*/
    	game.setDifficulty(Difficulties.ADVANCED);
    	
    	// choosing mode

    	int modeId = gameMode.getCheckedRadioButtonId();
    	RadioButton modeBtn = (RadioButton) activity.findViewById(modeId);
    	int mode = gameMode.indexOfChild(modeBtn);
    	
    	switch(mode){
    	case 0:
    		game.setMode(Modes.FREEPLAY);
    		break;
    	case 1:
    		game.setMode(Modes.PRACTICE);
    		break;
    	case 2:
    		game.setMode(Modes.CHALLENGE);
    		break;
    	}
    	
    	
    	
    	// choosing scale
    	/*if(((String)scale.getSelectedItem()).equals("Major"))
    	{
    		game.setScale(ScaleTypes.MAJOR);
    	}
    	if(((String)scale.getSelectedItem()).equals("Pentatonic"))
    	{
    		game.setScale(ScaleTypes.PENTATONIC);
    	}*/
    	game.setScale(ScaleTypes.MAJOR);
    	SoundInfo mi = loadNotes();
    	if (game.getInstrumentType() == InstrumentTypes.PIANO)
    	{
    		IMusicInstrument piano = MusicInstrumentFactory.makeInstrument(mi, InstrumentTypes.PIANO, game.getScale());
    		game.setInstrument(piano);
    	}
    	else if (game.getInstrumentType() == InstrumentTypes.GUITAR)
    	{
    		IMusicInstrument guitar = MusicInstrumentFactory.makeInstrument(mi, InstrumentTypes.GUITAR, game.getScale());
    		game.setInstrument(guitar);
    	}
    	
    	// choosing interval range
        int left = interval.getLeftIndex();
        int right = interval.getRightIndex();
        
        switch(left){
	        case 0:
	        	break;
	        case 1:
	        	break;
	        case 2:
	        	break;
	        case 3:
	        	break;
	        case 4:
	        	break;
	        case 5:
	        	break;
	        case 6:
	        	break;
	        case 7:
	        	break;
	        case 8:
	        	break;
	        case 9:
	        	break;
	        case 10:
	        	break;
	        case 11:
	        	break;
	        default: // set to first interval
        }
    	
        switch(right){
	        case 0:
	        	break;
	        case 1:
	        	break;
	        case 2:
	        	break;
	        case 3:
	        	break;
	        case 4:
	        	break;
	        case 5:
	        	break;
	        case 6:
	        	break;
	        case 7:
	        	break;
	        case 8:
	        	break;
	        case 9:
	        	break;
	        case 10:
	        	break;
	        case 11:
	        	break;
	        default: // set to last interval
        }
        
    	// choosing direction
    	int dirId = direction.getCheckedRadioButtonId();
    	RadioButton dirBtn = (RadioButton) activity.findViewById(dirId);
    	int dir = gameMode.indexOfChild(dirBtn);
    	
    	switch(dir){
    	case 0: // low-high
    		break;
    	case 1: // high-low
    		break;
    	case 2: // both
    		break;
    	}
    	
    	//saving user preferences
    	LyraProps.getInstance(activity).getUserPreferences().setGameMode(game.getMode());
    	LyraProps.getInstance(activity).getUserPreferences().setInstrumentType(game.getInstrumentType());
    	LyraProps.getInstance(activity).saveProps();
    }

	private SoundInfo loadNotes() {
		SoundPool sp = new SoundPool(12, AudioManager.STREAM_MUSIC, 0);

		SoundInfo mi = new SoundInfo();

		int[] notes = null;

		// For piano instrument type load the piano sounds
		if (game.getInstrumentType() == InstrumentTypes.PIANO) {
			Class<raw> raw = R.raw.class;
			Field[] fields = raw.getFields();
			ArrayList<Integer> notesList = new ArrayList<Integer>();
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				if (f.getName().startsWith("p")) {
					try {
						notesList.add(sp.load(activity, f.getInt(null), 1));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}

			// Convert it to an int array
			notes = new int[notesList.size()];
			for (int i = 0; i < notesList.size(); i++) {
				notes[i] = notesList.get(i).intValue();
			}
		} else if (game.getInstrumentType() == InstrumentTypes.GUITAR) {
			// TODO: load the guitar sounds
			Class<raw> raw = R.raw.class;
			Field[] fields = raw.getFields();
			ArrayList<Integer> notesList = new ArrayList<Integer>();
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				if (f.getName().startsWith("g")) {
					try {
						notesList.add(sp.load(activity, f.getInt(null), 1));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}

			// Convert it to an int array
			notes = new int[notesList.size()];
			for (int i = 0; i < notesList.size(); i++) {
				notes[i] = notesList.get(i).intValue();
			}
		}

		mi.setSoundNotes(notes);
		mi.setSoundPool(sp);

		return mi;
	}
}
