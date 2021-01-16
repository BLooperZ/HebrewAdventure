;;; Sierra Script 1.0 - (do not remove this comment)
(script# 120)
(include sci.sh)
(use Main)
(use Class_255_0)
(use Follow)
(use Chase)
(use Sound)
(use Cycle)
(use Game)
(use User)
(use Feature)
(use Obj)

(public
	rm120 0
)

(local
	local0
	local1
	local2 =  1
	local3
	local4
	local5
	local6
	local7
	userPrevDir
	local9
	[local10 4] = [1 0 3 2]
	local14
	local15
	local16
	local17
	local18 =  3
	local19
)
(procedure (localproc_1f54 param1 param2)
	(DirLoop
		param1
		(GetAngle
			(param1 x?)
			(param1 y?)
			(param2 x?)
			(param2 y?)
		)
	)
)

(procedure (localproc_1f80 param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5)
	(= temp1 (+ (= temp0 (- (param2 x?) 10)) 19))
	(= temp3 (+ (= temp2 (- (param2 y?) 20)) 20))
	(switch (param1 loop?)
		(0
			(= temp4 (+ (param1 x?) 37))
			(= temp5 (- (param1 y?) 13))
		)
		(1
			(= temp4 (- (param1 x?) 38))
			(= temp5 (- (param1 y?) 13))
		)
		(2
			(= temp4 (- (param1 x?) 8))
			(= temp5 (- (param1 y?) 10))
		)
		(3
			(= temp4 (+ (param1 x?) 6))
			(= temp5 (- (param1 y?) 10))
		)
	)
	(return
		(if
			(and
				(<= temp0 temp4)
				(<= temp4 temp1)
				(<= temp2 temp5)
				(<= temp5 temp3)
			)
			(return 1)
		else
			(return 0)
		)
	)
)

(procedure (localproc_2066 param1 &tmp temp0 temp1)
	(= temp0 1000)
	(= temp1 0)
	(= temp0 1000)
	(while (< temp1 argc)
		(if (< [param1 temp1] temp0) (= temp0 [param1 temp1]))
		(++ temp1)
	)
	(return temp0)
)

(procedure (localproc_2096)
	(= local2 0)
	(User canControl: 0)
)

(procedure (localproc_20a4)
	(= local2 1)
	(User canControl: 1)
)

(procedure (localproc_20b2)
	(gEgo
		view: 200
		setMotion: 0
		setLoop: -1
		setCel: -1
		setCycle: Walk
	)
	(legs view: 201 setLoop: -1 setCycle: 0)
)

(instance rm120 of Rm
	(properties
		picture 120
		picAngle 80
	)
	
	(method (init &tmp [temp0 50])
		(User canInput: 0 canControl: 1)
		(Load 128 195)
		(Load 128 196)
		(Load 128 198)
		(Load 128 200)
		(Load 128 201)
		(Load 128 202)
		(Load 128 203)
		(Load 128 204)
		(Load 128 205)
		(Load 128 206)
		(Load 128 207)
		(Load 128 208)
		(Load 128 209)
		(Load 128 211)
		(Load 132 18)
		(Load 132 19)
		(Load 132 33)
		(super init:)
		(gEgo
			view: 200
			loop: 0
			setLoop: -1
			posn: 62 85
			setStep: 3 1
			illegalBits: -32768
			baseSetter: newBase
			ignoreActors: 0
			init:
		)
		(badGuy init:)
		(bgLegs init:)
		(legs init:)
		(Scott init:)
		(Mark init: setMotion: Follow Scott 10)
		(egoPower init:)
		(bgPower init:)
		(= global159 0)
		(self setScript: StartScript)
	)
	
	(method (doit &tmp [temp0 50])
		(super doit:)
		(if (and (badGuy isBlocked:) (not local15))
			(BadGuy changeState: 15)
		)
		(if (and (gEgo inRect: 0 0 152 86) (not local15))
			(ScottScript changeState: 10)
		)
		(if
		(and (gEgo inRect: 0 87 152 189) (not local15))
			(ScottScript changeState: 7)
		)
		(if
		(and (gEgo inRect: 153 0 319 86) (not local15))
			(ScottScript changeState: 4)
		)
		(if
		(and (gEgo inRect: 153 87 319 189) (not local15))
			(ScottScript changeState: 1)
		)
		(if global219 (-- local18))
		(if (not local18)
			(= local18 3)
			(= local16 1)
			(= local17 1)
		)
	)
	
	(method (handleEvent param1)
		(super handleEvent: param1)
		(if (param1 claimed?) (return))
		(switch (param1 type?)
			(4
				(cond 
					(
						(and
							(or
								(== (param1 message?) 74)	;J
								(== (param1 message?) 106)	;j
								(== (param1 message?) 231)	;�
							)
							local2
						)
						(self setScript: Punch)
					)
					(
						(and
							(or
								(== (param1 message?) 77)	;M
								(== (param1 message?) 109)	;m
								(== (param1 message?) 246)	;�
							)
							local2
						)
						(self setScript: EgoBlock)
					)
					(
						(and
							(or
								(== (param1 message?) 78)	;N	- it's some debug key, shouldn't really be in release version...
								(== (param1 message?) 110)	;n
							)
							local2
						)
						(gCast showSelf:)
					)
					(
						(and
							(== (param1 message?) `@r)
							local2
						)
						; Zvika's cheat ;-)
						(= local14 0) ;(= bgHealth 0)
						(= local15 1) ; (= egoHealth 1)
						(localproc_2096) ; (NoAttack)
						(BadGuy changeState: 16)
						(ScottScript changeState: 13)
						(global2 setScript: BgDead) ; (curRoom setScript: BgDead)
					)

				)
			)
		)
	)
)

(instance StartScript of Script
	(properties)
	
	(method (changeState theState &tmp [temp0 50])
		(switch (= state theState)
			(0
				(localproc_2096)
				(= local15 1)
				(balloon init:)
				(= seconds 3)
			)
			(1
				(balloon dispose:)
				(= local15 0)
				(localproc_20a4)
				(client setScript: 0)
			)
		)
	)
)

(instance Punch of Script
	(properties)
	
	(method (changeState theState &tmp [temp0 50])
		(switch (= state theState)
			(0
				(= userPrevDir (User prevDir?))
				(localproc_2096)
				(if (gEgo mover?)
					(= local6 ((gEgo mover?) x?))
					(= local7 ((gEgo mover?) y?))
				else
					(= local6 0)
					(= local7 0)
				)
				(gEgo view: 202 setCel: 0 setMotion: 0)
				(if (and (not local5) (not local4))
					(BadGuy changeState: 7)
				)
				(gEgo setCycle: End self)
			)
			(1
				(= local16 4)
				(if
					(and
						(localproc_1f80 gEgo badGuy)
						(!= (gEgo loop?) (badGuy loop?))
					)
					(if (not local3)
						(clang1 play:)
						(= local9 1)
						(= local16 -8)
						(= local17 4)
						(BadGuy changeState: 12)
					else
						(clang2 play:)
						(= global176 0)
						(= local16 4)
					)
				)
				(= cycles 2)
			)
			(2
				(localproc_20b2)
				(if (or local6 local7)
					(gEgo setMotion: MoveTo local6 local7)
				)
				(= local1 0)
				(localproc_20a4)
				(User prevDir: userPrevDir)
				(client setScript: 0)
			)
		)
	)
)

(instance EgoBump of Script
	(properties)
	
	(method (doit)
		(if (and (gEgo isBlocked:) local1 (not local14))
			(self cue:)
		)
		(if (== (legs view?) 208)
			(legs
				setLoop: (gEgo loop?)
				setPri: (gEgo priority?)
				x: (gEgo x?)
				y: (- (gEgo y?) 1)
			)
		)
		(super doit:)
	)
	
	(method (changeState theState &tmp [temp0 50] temp50 temp51)
		(switch (= state theState)
			(0
				(localproc_2096)
				(if (== (gEgo loop?) (badGuy loop?))
					(switch (gEgo loop?)
						(0 (= temp50 20) (= temp51 0))
						(1 (= temp50 -20) (= temp51 0))
						(2 (= temp50 0) (= temp51 10))
						(3 (= temp50 0) (= temp51 -10))
					)
				else
					(switch (gEgo loop?)
						(0 (= temp50 -20) (= temp51 0))
						(1 (= temp50 20) (= temp51 0))
						(2 (= temp50 0) (= temp51 -10))
						(3 (= temp50 0) (= temp51 10))
					)
					(legs view: 208 setCycle: Fwd)
					(gEgo
						view: 198
						signal: (& (gEgo signal?) $fbff)
						setLoop:
						cel: 0
						setCycle: 0
					)
				)
				(gEgo
					setMotion: MoveTo (+ (gEgo x?) temp50) (+ (gEgo y?) temp51) self
				)
				(= local1 1)
			)
			(1
				(localproc_20b2)
				(= local1 0)
				(localproc_20a4)
				(self dispose:)
			)
		)
	)
)

(instance EgoBlock of Script
	(properties)
	
	(method (changeState theState &tmp [temp0 50])
		(switch (= state theState)
			(0
				(= userPrevDir (User prevDir?))
				(localproc_2096)
				(= local0 1)
				(if (gEgo mover?)
					(= local6 ((gEgo mover?) x?))
					(= local7 ((gEgo mover?) y?))
				else
					(= local6 0)
					(= local7 0)
				)
				(gEgo view: 209 setCel: 0 setMotion: 0)
				(proc0_10)
				(gEgo setCycle: End self)
			)
			(1 (= cycles 5))
			(2 (gEgo setCycle: Beg self))
			(3
				(localproc_20b2)
				(if (or local6 local7)
					(gEgo setMotion: MoveTo local6 local7)
				)
				(= local0 0)
				(= local16 2)
				(localproc_20a4)
				(User prevDir: userPrevDir)
				(client setScript: 0)
			)
		)
	)
)

(instance BadGuy of Script
	(properties)
	
	(method (doit)
		(if (== (bgLegs view?) 206)
			(bgLegs
				setLoop: (badGuy loop?)
				setPri: (badGuy priority?)
				x: (badGuy x?)
				y: (- (badGuy y?) 1)
			)
		)
		(super doit:)
	)
	
	(method (changeState theState &tmp [temp0 50] temp50 temp51)
		(switch (= state theState)
			(0
				(cond 
					((< (badGuy loop?) 2)
						(if (> (badGuy y?) (gEgo y?))
							(badGuy posn: (badGuy x?) (- (badGuy y?) 1))
						else
							(badGuy posn: (badGuy x?) (+ (badGuy y?) 1))
						)
					)
					((> (badGuy x?) (gEgo x?)) (badGuy posn: (- (badGuy x?) 1) (badGuy y?)))
					(else (badGuy posn: (+ (badGuy x?) 1) (badGuy y?)))
				)
				(badGuy setMotion: Chase gEgo 45 self)
			)
			(1 (= cycles 1))
			(2
				(switch (Random 1 2)
					(1 (self changeState: 3))
					(2 (self changeState: 12))
				)
			)
			(3
				(localproc_1f54 badGuy gEgo)
				(= local5 1)
				(badGuy
					view: 205
					setCel: 0
					setMotion: 0
					signal: (& (badGuy signal?) $fbff)
				)
				(= cycles 2)
			)
			(4 (badGuy setCycle: End self))
			(5
				(= local17 4)
				(if
				(and (localproc_1f80 badGuy gEgo) (not local1))
					(if (not local0)
						(clang1 play:)
						(= local17 -8)
						(= local16 4)
						(global2 setScript: EgoBump)
					else
						(clang2 play:)
					)
				)
				(= cycles 2)
			)
			(6
				(= local5 0)
				(if (< (Random 1 100) 21)
					(self changeState: 3)
				else
					(self changeState: 12)
				)
			)
			(7
				(if
					(or
						(!= (badGuy loop?) [local10 (gEgo loop?)])
						(>
							(GetDistance
								(gEgo x?)
								(gEgo y?)
								(badGuy x?)
								(badGuy y?)
							)
							55
						)
					)
					(self changeState: 15)
				else
					(= cycles (Random 2 7))
				)
			)
			(8
				(= local3 1)
				(localproc_1f54 badGuy gEgo)
				(badGuy view: 207 setCel: 0 setMotion: 0)
				(proc0_10)
				(badGuy setCycle: End self)
			)
			(9 (= cycles 5))
			(10 (badGuy setCycle: Beg self))
			(11
				(= local3 0)
				(= local17 2)
				(self changeState: 12)
			)
			(12
				(= local4 1)
				(bgLegs view: 206 setCycle: Fwd)
				(badGuy
					view: (if local9 198 else 203)
					setLoop: (badGuy loop?)
					setCel: (if local9 1 else -1)
					setCycle: (if local9 0 else Walk)
				)
				(self changeState: 13)
			)
			(13
				(switch (badGuy loop?)
					(0 (= temp50 -20) (= temp51 0))
					(1 (= temp50 20) (= temp51 0))
					(2 (= temp50 0) (= temp51 -10))
					(3 (= temp50 0) (= temp51 10))
				)
				(badGuy
					setMotion: MoveTo (+ (badGuy x?) temp50) (+ (badGuy y?) temp51) self
				)
			)
			(14
				(= local9 0)
				(self changeState: 15)
			)
			(15
				(bgLegs view: 204 setCycle: 0)
				(badGuy
					view: 203
					setLoop: -1
					setCel: -1
					setCycle: Walk
					setMotion: 0
				)
				(= local5 0)
				(= local4 0)
				(= local9 0)
				(self changeState: 0)
			)
			(16 (badGuy setScript: 0))
		)
	)
)

(instance ScottScript of Script
	(properties)
	
	(method (changeState theState)
		(switch (= state theState)
			(0)
			(1
				(if (Scott inRect: 153 87 319 189)
					(Scott setMotion: MoveTo 228 70 self)
				else
					(self changeState: 2)
				)
			)
			(2
				(if (and (!= (Scott x?) 82) (!= (Scott y?) 72))
					(Scott setMotion: MoveTo 82 72 self)
				)
			)
			(3 (localproc_1f54 Scott gEgo))
			(4
				(if (Scott inRect: 153 0 319 86)
					(Scott setMotion: MoveTo 230 105 self)
				else
					(self changeState: 5)
				)
			)
			(5
				(if (and (!= (Scott x?) 83) (!= (Scott y?) 103))
					(Scott setMotion: MoveTo 83 103 self)
				)
			)
			(6 (localproc_1f54 Scott gEgo))
			(7
				(if (Scott inRect: 0 87 152 189)
					(Scott setMotion: MoveTo 82 72 self)
				else
					(self changeState: 8)
				)
			)
			(8
				(if (and (!= (Scott x?) 228) (!= (Scott y?) 70))
					(Scott setMotion: MoveTo 228 70 self)
				)
			)
			(9 (localproc_1f54 Scott gEgo))
			(10
				(if (Scott inRect: 0 0 152 86)
					(Scott setMotion: MoveTo 228 70 self)
				else
					(self changeState: 11)
				)
			)
			(11
				(if
				(and (!= (Scott x?) 230) (!= (Scott y?) 105))
					(Scott setMotion: MoveTo 230 105 self)
				)
			)
			(12 (localproc_1f54 Scott gEgo))
			(13
				(Scott setMotion: MoveTo 153 105 self)
			)
			(14 (localproc_1f54 Scott gEgo))
		)
	)
)

(instance EgoDead of Script
	(properties)
	
	(method (changeState theState)
		(switch (= state theState)
			(0
				(proc0_2)
				(badGuy setMotion: 0 setCycle: 0)
				(gEgo illegalBits: 0 setMotion: 0)
				(proc0_10)
				(gEgo
					view: 211
					setLoop: (if (> (gEgo x?) 153) 0 else 1)
					setPri: (gEgo priority?)
					y: (+ (gEgo y?) 32)
					cycleSpeed: 1
					setCycle: End self
				)
				(legs dispose:)
			)
			(1
				(boom play:)
				(ShakeScreen 10 3)
				(proc255_0 120 0 67 -1 130 70 280)
				(proc0_17 0 0 9 13)
			)
		)
	)
)

(instance BgDead of Script
	(properties)
	
	(method (doit)
		(legs cel: (gEgo cel?) loop: (gEgo loop?))
		(if (not (-- local18))
			(= local18 (Random 1 3))
			(if (badGuy mover?) (badGuy setLoop: (Random 0 3)))
		)
		(bgLegs
			setLoop: (badGuy loop?)
			setPri: (badGuy priority?)
			x: (badGuy x?)
			y: (- (badGuy y?) 1)
		)
		(super doit:)
	)
	
	(method (changeState theState &tmp [temp0 50] temp50 temp51 temp52 temp53 temp54 temp55 badGuyX badGuyY)
		(switch (= state theState)
			(0
				(proc0_2)
				(= local18 2)
				(badGuy ignoreActors: 1 illegalBits: 0)
				(bgLegs ignoreActors: 1)
				(localproc_20b2)
				(gEgo ignoreActors: 1)
				(legs ignoreActors: 1)
				(if (> (gEgo x?) 160)
					(gEgo setMotion: MoveTo 130 90)
				else
					(gEgo setMotion: MoveTo 170 90)
				)
				(= badGuyX (badGuy x?))
				(= badGuyY (badGuy y?))
				(= temp50 (GetDistance badGuyX badGuyY 80 78))
				(= temp51 (GetDistance badGuyX badGuyY 98 73))
				(= temp52 (GetDistance badGuyX badGuyY 128 68))
				(= temp53 (GetDistance badGuyX badGuyY 187 68))
				(= temp54 (GetDistance badGuyX badGuyY 213 73))
				(= temp55 (GetDistance badGuyX badGuyY 241 78))
				(bgLegs view: 206 setCycle: Fwd)
				(badGuy view: 198 setCel: 1 setCycle: 0)
				(switch (localproc_2066 temp50 temp51 temp52 temp53 temp54 temp55)
					(temp50
						(badGuy setMotion: MoveTo 80 78 self)
					)
					(temp51
						(badGuy setMotion: MoveTo 98 73 self)
					)
					(temp52
						(badGuy setMotion: MoveTo 128 68 self)
					)
					(temp53
						(badGuy setMotion: MoveTo 187 68 self)
					)
					(temp54
						(badGuy setMotion: MoveTo 213 73 self)
					)
					(temp55
						(badGuy setMotion: MoveTo 241 78 self)
					)
				)
			)
			(1
				(bgLegs dispose:)
				(if (<= (gEgo y?) (+ (badGuy y?) 6))
					(= temp50 1)
				else
					(= temp50 -1)
				)
				(badGuy
					view: 211
					illegalBits: 0
					setLoop: (if (> (badGuy x?) 150) 2 else 3)
					setPri: (+ (gEgo priority?) temp50)
					y: (+ (badGuy y?) 32)
					cycleSpeed: 1
					setMotion: 0
					setCycle: End self
				)
				(if (== (badGuy loop?) 2)
					(= local19 1)
				else
					(= local19 -1)
				)
			)
			(2
				(boom play:)
				(ShakeScreen 10 3)
				(gGame changeScore: 100)
				(Scott illegalBits: 0 setPri: (+ (badGuy priority?) 1))
				(Mark setPri: (Scott priority?))
				(Scott
					setMotion:
						MoveTo
						(+ (badGuy x?) (* local19 40))
						(- (badGuy y?) 32)
						self
				)
			)
			(3
				(balloon
					init:
					setLoop: 2
					setCel: (if (< (Scott x?) 160) 0 else 1)
				)
				(= seconds 3)
			)
			(4
				(balloon dispose:)
				(proc0_10)
				(Scott dispose:)
				(Mark dispose:)
				(= cycles 2)
			)
			(5
				(gEgo setCel: 0 setLoop: 2)
				(legs setLoop: 2 posn: (gEgo x?) (- (gEgo y?) 1))
				(guy
					init:
					setLoop: 0
					posn: (gEgo x?) (- (gEgo y?) 16)
					setPri: (+ (gEgo priority?) 2)
					setMotion: MoveTo (- (gEgo x?) 5) (+ (gEgo y?) 3) self
				)
				(square init: stopUpd:)
			)
			(6
				(guy
					setLoop: 1
					setCel: 0
					setCycle: 0
					setMotion: MoveTo (- (gEgo x?) 5) (+ (gEgo y?) 30) self
				)
			)
			(7
				(guy view: 68 setLoop: -1 setStep: 6 3 setCycle: Walk)
				(guy
					setMotion: MoveTo (+ (guy x?) (* local19 12)) (guy y?) self
				)
			)
			(8
				(if (< (guy y?) (badGuy y?))
					(guy
						setPri: (- (gEgo priority?) 1)
						setMotion:
							MoveTo
							(- (badGuy x?) (* local19 5))
							(+ (badGuy y?) 10)
							self
					)
				else
					(= cycles 2)
				)
			)
			(9
				(guy
					setMotion:
						MoveTo
						(+ (badGuy x?) (* local19 20))
						(+ (badGuy y?) 10)
						self
				)
			)
			(10
				(guy
					setMotion: MoveTo (+ (badGuy x?) (* local19 40)) (badGuy y?) self
				)
			)
			(11
				(guy dispose:)
				(gLongSong stop:)
				(= cycles 2)
			)
			(12 (global2 newRoom: 94))
		)
	)
)

(instance legs of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 201
			setLoop: (gEgo loop?)
			setCel: (gEgo cel?)
			posn: (gEgo x?) (- (gEgo y?) 1)
			setPri: (gEgo priority?)
			ignoreActors: 1
		)
	)
	
	(method (doit)
		(super doit:)
		(if (and (not (gEgo mover?)) (== local2 1))
			(= local16 0)
		)
		(if (and (gEgo mover?) (!= (self view?) 208))
			(self
				setLoop: (gEgo loop?)
				setCel: (gEgo cel?)
				setPri: (gEgo priority?)
				x: (gEgo x?)
				y: (- (gEgo y?) 1)
			)
		)
	)
)

(instance bgLegs of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 204
			setLoop: (badGuy loop?)
			setCel: (badGuy cel?)
			posn: (badGuy x?) (- (badGuy y?) 1)
			setPri: (badGuy priority?)
			ignoreActors: 1
		)
	)
	
	(method (doit)
		(super doit:)
		(if (and (badGuy mover?) (!= (self view?) 206))
			(self
				setLoop: (badGuy loop?)
				setCel: (badGuy cel?)
				setPri: (badGuy priority?)
				x: (badGuy x?)
				y: (- (badGuy y?) 1)
			)
		)
	)
)

(instance badGuy of Act
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 203
			loop: 1
			posn: 272 86
			setCycle: Walk
			setStep: 3 1
			illegalBits: -32768
			setScript: BadGuy
			ignoreActors: 0
			baseSetter: newBase
		)
	)
)

(instance Scott of Act
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 195
			loop: 0
			posn: 159 79
			setCycle: Walk
			setStep: 6 3
			illegalBits: -32768
			ignoreActors: 1
			setScript: ScottScript
		)
	)
)

(instance Mark of Act
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 196
			loop: 0
			posn: 162 82
			setCycle: Walk
			setStep: 6 3
			illegalBits: -32768
			ignoreActors: 1
		)
	)
)

(instance guy of Act
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 97
			loop: 0
			setCycle: Walk
			setStep: 3 3
			illegalBits: 0
			ignoreActors: 1
		)
	)
)

(instance egoPower of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 211
			setLoop: 4
			setCel: 0
			posn: 78 12
			setPri: 14
			setCycle: 0
			ignoreActors: 1
		)
	)
	
	(method (doit)
		(if (< (self x?) 32)
			(self setCycle: Fwd)
		else
			(self setCycle: 0 setCel: 0)
		)
		(if local16
			(if (> (- (self x?) local16) 78)
				(self x: 78)
			else
				(self x: (- (self x?) local16))
			)
			(= local16 0)
		)
		(if (and (<= (self x?) 11) (not local15))
			(= local14 1)
			(localproc_2096)
			(global2 setScript: EgoDead)
			(self dispose:)
		)
		(super doit:)
	)
)

(instance bgPower of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 211
			setLoop: 4
			setCel: 0
			posn: 230 12
			setPri: 14
			setCycle: 0
			ignoreActors: 1
		)
	)
	
	(method (doit)
		(if (> (self x?) 273)
			(self setCycle: Fwd)
		else
			(self setCycle: 0 setCel: 0)
		)
		(if local17
			(if (< (+ (self x?) local17) 230)
				(self x: 230)
			else
				(self x: (+ (self x?) local17))
			)
			(= local17 0)
		)
		(if (and (>= (self x?) 296) (not local14))
			(= local15 1)
			(localproc_2096)
			(BadGuy changeState: 16)
			(ScottScript changeState: 13)
			(global2 setScript: BgDead)
			(self dispose:)
		)
		(super doit:)
	)
)

(instance square of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 117
			setLoop: 1
			setCel: 1
			posn: (gEgo x?) (- (gEgo y?) 18)
			setPri: (+ (gEgo priority?) 1)
			setCycle: 0
			ignoreActors: 1
		)
	)
)

(instance balloon of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 117
			setLoop: 3
			setCel: 0
			posn: (Scott x?) (+ (Scott y?) 11)
			setPri: 15
			setCycle: 0
			ignoreActors: 1
		)
	)
)

(instance clang1 of Sound
	(properties
		number 18
		priority 2
	)
)

(instance clang2 of Sound
	(properties
		number 19
		priority 2
	)
)

(instance boom of Sound
	(properties
		number 33
		priority 3
	)
)

(instance newBase of Code
	(properties)
	
	(method (doit param1)
		(param1 brTop: (- (param1 y?) 4))
		(param1 brBottom: (+ (param1 y?) 4))
		(param1 brLeft: (- (param1 x?) 20))
		(param1 brRight: (+ (param1 x?) 20))
	)
)
