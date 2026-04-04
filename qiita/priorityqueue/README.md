# priorityqueue

PriorityQueue\<E>のObject[] queueを、int[] queue等に変えた優先度付きキュー

- DEFAULT_INITIAL_CAPACITYやgrow()は、PriorityQueueと同じ
- siftUpUsingComparatorとsiftDownUsingComparatorから、descendingを判断するcompare()を呼び出す

本体コードは、PriorityQueueのjavadoc URLを元に、AIが生成したものを2割程度手直した。
テストコードは、作成したjavaソースファイルを元に、AIが生成したものがほぼそのまま使えた。
