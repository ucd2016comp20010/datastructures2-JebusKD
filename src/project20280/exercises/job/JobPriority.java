package project20280.exercises.job;

public enum JobPriority {
	CRITICAL
 {
		@Override
		public int comp(JobPriority j) {
			switch (j) {
			case CRITICAL: return 0;
			case HIGH: return 1;
			case MEDIUM: return 2;
			case LOW: return 3;
			default: return 0;
			}
		}
	},
 	HIGH
 	{
		@Override
		public int comp(JobPriority j) {
			switch (j) {
			case CRITICAL: return -1;
			case HIGH: return 0;
			case MEDIUM: return 1;
			case LOW: return 2;
			default: return 0;
			}
		}
	},
	MEDIUM
	{
		@Override
		public int comp(JobPriority j) {
			switch (j) {
			case CRITICAL: return -2;
			case HIGH: return -1;
			case MEDIUM: return 0;
			case LOW: return 1;
			default: return 0;
			}
		}
	},
	LOW
	{
		@Override
		public int comp(JobPriority j) {
			switch (j) {
			case CRITICAL: return -3;
			case HIGH: return -2;
			case MEDIUM: return -1;
			case LOW: return 0;
			default: return 0;
			}
		}
	};
	
	abstract public int comp(JobPriority j);
}