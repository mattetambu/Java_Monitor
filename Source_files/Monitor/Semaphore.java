package Monitor;
		if (p_count <= 0) {
			p_suspended.extract();
			notifyAll();
		}
	}
}