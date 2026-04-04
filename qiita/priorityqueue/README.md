# priorityqueue

PriorityQueue\<E>のObject[] queueを、int[] queue等に変えた優先度付きキュー

- DEFAULT_INITIAL_CAPACITYやgrow()は、PriorityQueueと同じ
- siftUpUsingComparatorとsiftDownUsingComparatorから、descendingを判断するcompare()を呼び出す

